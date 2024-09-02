package com.musashi.weatherapp.ui.screen.summary

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.musashi.weatherapp.application.NotificationHandler
import com.musashi.weatherapp.application.ReminderWorker
import com.musashi.weatherapp.domain.model.BookmarkModel
import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.domain.model.EmptyCity
import com.musashi.weatherapp.domain.preferences.LocalUserManager
import com.musashi.weatherapp.domain.repository.WeatherRepository
import com.musashi.weatherapp.ui.helper.isCitySetAsDefault
import com.musashi.weatherapp.ui.helper.returnWeatherCode
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val weatherRepository: WeatherRepository,
    private val userLocalUserManager: LocalUserManager
): ViewModel() {


    /////////////////////////////////////////////////Initialization//////////////////////////////////////////////////////
    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()

    private val workManager = WorkManager.getInstance(context)

    private val notificationHandler = NotificationHandler(context)

    init {
//        createDatabaseFromRawJson(context)
        getListOfCountries()
        readLocalSetting()
        loadBookmark()
//        scheduleReminder(1, TimeUnit.MINUTES)
    }
    /////////////////////////////////////////////////Public functions//////////////////////////////////////////////////////

    private fun scheduleReminder(
        duration: Long,
        unit: TimeUnit
    ){
        val myWorkRequestBuilder = PeriodicWorkRequestBuilder<ReminderWorker>(duration, unit, 15, TimeUnit.SECONDS)

        workManager.enqueue(myWorkRequestBuilder.build())
    }


    fun addToBookmark(){
        viewModelScope.launch {
            weatherRepository.upsertBookmark(cityModel = state.value.currentCity)
            loadBookmark()
        }
    }

    fun deleteFromBookmark(city: CityModel){
        viewModelScope.launch {
            weatherRepository.deleteBookmark(cityModel = city)
        }
        loadBookmark()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun onSetDefaultCityClick(){
        if (isCitySetAsDefault(state.value)) {
            saveLocalSetting("", "", false)
            showNotification(
                title = "Default city is cleared",
                content = "",
                weatherCode = returnWeatherCode(
                    state.value.weatherCurrentStatus?.current?.weatherCode ?: 0
                ).imageId
            )
        } else {
            saveLocalSetting(
                state.value.currentCity.countryName,
                state.value.currentCity.cityName,
                true
            )
            showNotification(
                title = "${state.value.currentCity.cityName} is the default city.",
                content = "Current temperature: ${state.value.weatherCurrentStatus?.current?.temperature2m}°C",
                weatherCode = returnWeatherCode(
                    state.value.weatherCurrentStatus?.current?.weatherCode ?: 0
                ).imageId
            )
        }

    }

    fun setSelectedAsCurrentCity(city: CityModel){
        _state.update { it.copy(currentCity = city) }
        getCurrentCityWeather()
//        getLocation()
    }

    fun selectCountry(countryName: String){
        viewModelScope.launch {
            if (weatherRepository.getCities(countryName = countryName).first().isNotEmpty()) {
                _state.update { it.copy(isCountrySelected = true) }
                _state.update { it.copy(cities = weatherRepository.getCities(countryName = countryName).first()) }
            }else{
                _state.update { it.copy(isCountrySelected = false) }
            }
        }
    }

    fun selectCity(cityName: String, countryName: String){
        viewModelScope.launch {
            weatherRepository.getCity(
                cityName = cityName.replaceFirstChar { char -> char.uppercaseChar() },
                countryName = countryName.replaceFirstChar { char -> char.uppercaseChar() },
            )?.let { targetCity ->
                _state.update {
                    it.copy(
                        currentCity = CityModel(
                            id = targetCity.id,
                            countryName = targetCity.countryName,
                            countryNameFa = targetCity.countryNameFa,
                            cityName = targetCity.cityName,
                            cityNameFa = targetCity.cityNameFa,
                            latitude = targetCity.latitude,
                            longitude = targetCity.longitude
                        )
                    )
                }
            }?: _state.update {
                it.copy(
                    currentCity = EmptyCity.emptyCity
                )
            }
            getCurrentCityWeather()
//           getLocation()
        }
    }
    fun refresh() {
        viewModelScope.launch {
            _state.update { it.copy(isRefreshing = true) }
            if (state.value.errorHourly != null || state.value.errorCurrent != null) {
                loadBookmark()
                getCurrentCityWeather()
            }
            delay(500)
            _state.update { it.copy(isRefreshing = false) }
        }
    }
    /////////////////////////////////////////////////Private functions//////////////////////////////////////////////////////
    private fun saveLocalSetting(country: String, city: String, state: Boolean){
        viewModelScope.launch {
            userLocalUserManager.saveSelectedCity(city = city)
            userLocalUserManager.saveSelectedCountry(country = country)
            userLocalUserManager.saveBookmarkState(state = state)
            _state.update {
                it.copy(
                    isDefaultCitySet = state,
                    localCityCountry = Pair(first = country, second = city)
                )
            }
        }
    }

    private fun readLocalSetting(){
        viewModelScope.launch {
            val country =  userLocalUserManager.readSelectedCountry().first()
            val city =  userLocalUserManager.readSelectedCity().first()
            val bookmarkState = userLocalUserManager.readBookmarkState().first()
            _state.update {
                it.copy(
                    localCityCountry = Pair(
                        first = country,
                        second = city
                    ),
                    isDefaultCitySet = bookmarkState
                )
            }
            selectCountry(state.value.localCityCountry.first)
            selectCity(state.value.localCityCountry.second, state.value.localCityCountry.first)

        }
    }

//    private fun createDatabaseFromRawJson(context: Context) {
//
//        _state.update { it.copy(isDatabaseLoaded = false) }
//        viewModelScope.launch {
//            val count = weatherRepository.getTableCount()
//
//            if (count < 47868) {
//                val citiesJsonArray: JSONArray =
//                    context.resources.openRawResource(R.raw.world_cities_with_id).bufferedReader()
//                        .use {
//                            JSONArray(it.readText())
//                        }
//                citiesJsonArray.takeIf { it.length() > 0 }?.let { list ->
//                    for (index in 0 until list.length()) {
//                        val cityObj = list.getJSONObject(index)
//                        weatherRepository.upsertCity(
//                            CityModel(
//                                id = cityObj.getInt("id"),
//                                countryName = cityObj.getString("country"),
//                                cityName = cityObj.getString("city_ascii"),
//                                latitude = cityObj.getDouble("lat"),
//                                longitude = cityObj.getDouble("lng")
//                            )
//                        )
//                    }
//                }
//            }
//            _state.update { it.copy(isDatabaseLoaded = true) }
//        }
//    }

//    private fun getLocation(){
//        viewModelScope.launch {
//            weatherRepository
//                .getLocation(cityName = state.value.currentCity.cityName, countryName = state.value.currentCity.countryName)
//                .onLeft { error -> _state.update { it.copy(mapApiError = error.error.message) } }
//                .onRight { response -> _state.update { it.copy(mapApiResult = response) } }
//        }
//    }

    private fun getListOfCountries(){
        _state.update { it.copy(isDatabaseLoaded = false) }
        viewModelScope.launch {
            _state.update {
                it.copy(
                    countries = weatherRepository.getCountries().first(),
                    countriesFa = weatherRepository.getCountriesFa().first(),
                )
            }
        }
        _state.update { it.copy(isDatabaseLoaded = true) }
    }

    private fun getCurrentCityWeather(){
        if( state.value.currentCity != EmptyCity.emptyCity) {

            _state.update { it.copy(isWeatherLoading = true) }
            getCurrentWeather(
                latitude = state.value.currentCity.latitude,
                longitude = state.value.currentCity.longitude
            )
            getHourlyWeather(
                latitude = state.value.currentCity.latitude,
                longitude = state.value.currentCity.longitude
            )
            _state.update { it.copy(isWeatherLoading = false) }
        }
    }

    private fun getHourlyWeather(latitude: Double, longitude: Double){
        viewModelScope.launch {
            weatherRepository.getFullWeathers(
                latitude = latitude,
                longitude = longitude,
            ).onLeft { error ->
                _state.update {
                    it.copy(errorHourly = error.error.message)
                }
            }.onRight { weathers ->
                _state.update {
                    it.copy(weatherFullStatus = weathers, errorHourly = null)
                }
            }
        }
    }
    private fun getCurrentWeather(latitude: Double, longitude: Double){
        viewModelScope.launch {
            weatherRepository.getCurrentWeathers(
                latitude = latitude,
                longitude = longitude,
            ).onLeft { error ->
                _state.update {
                    it.copy(errorCurrent = error.error.message)
                }
            }.onRight { weather ->
                _state.update {
                    it.copy(weatherCurrentStatus = weather, errorCurrent = null)
                }
            }
        }
    }

    private fun loadBookmark(){
        viewModelScope.launch {
            _state.update { it.copy(
                bookmarkedCities = weatherRepository.getBookmark().first(),
                isBookmarkListLoaded = true,
            ) }
            if(state.value.isBookmarkListLoaded) {
                getBookmarkedCityWeather()
            }
        }
    }

    private fun getBookmarkedCityWeather(){
        val result = mutableListOf<BookmarkModel>()
        viewModelScope.launch {
            _state.update { it.copy(isBookmarkWeatherReceived = false) }
            state.value.bookmarkedCities.forEach { city ->
                weatherRepository.getCurrentWeathers(
                    latitude = city.latitude,
                    longitude = city.longitude,
                ).onLeft { error ->
                    result += BookmarkModel(city, 0.0, 0, error = error.error.message)
                    _state.update { it.copy(result = result) }
                }.onRight { weathers ->
                    result += BookmarkModel(city, weathers.current.temperature2m, weathers.current.weatherCode, error = null)
                    _state.update { it.copy(result = result) }
                }
            }
            _state.update { it.copy(isBookmarkWeatherReceived = true) }
        }
    }

    private fun showNotification(
        title: String,
        content: String,
        weatherCode: Int,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            notificationHandler.showSimpleNotification(
                title = title,
                content = content,
                weatherCode = weatherCode
            )
        }
    }
}