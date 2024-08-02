package com.musashi.weatherapp.ui.screen.summary

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musashi.weatherapp.domain.model.BookmarkModel
import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.domain.model.EmptyCity
import com.musashi.weatherapp.domain.preferences.LocalUserManager
import com.musashi.weatherapp.domain.repository.WeatherRepository
import com.musashi.weatherapp.ui.helper.isCitySetAsDefault
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    init {
//        createDatabaseFromRawJson(context)
        getListOfCountries()
        readLocalSetting()
        loadBookmark()
    }
    /////////////////////////////////////////////////Public functions//////////////////////////////////////////////////////
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

    fun onSetDefaultCityClick(){
        if(isCitySetAsDefault(state.value)){
            saveLocalSetting("", "", false)
        }else{
            saveLocalSetting(state.value.currentCity.countryName, state.value.currentCity.cityName, true)
        }
    }

    fun setSelectedAsCurrentCity(city: CityModel){
        _state.update { it.copy(currentCity = city) }
        getCurrentCityWeather()
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
        }
    }
    fun refresh() {
        if(state.value.error != null) {
            loadBookmark()
            getCurrentCityWeather()
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
            getWeather(
                latitude = state.value.currentCity.latitude,
                longitude = state.value.currentCity.longitude
            )
            _state.update { it.copy(isWeatherLoading = false) }
        }
    }

    private fun getWeather(latitude: Double, longitude: Double){
        viewModelScope.launch {
            weatherRepository.getWeathers(
                latitude = latitude,
                longitude = longitude,
            ).onLeft { error ->
                _state.update {
                    it.copy(error = error.error.message)
                }
            }.onRight { weathers ->
                _state.update {
                    it.copy(weatherStatus = weathers, error = null)
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
                weatherRepository.getWeathers(
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
}