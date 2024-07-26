package com.musashi.weatherapp.ui.screen.summary

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musashi.weatherapp.R
import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.domain.preferences.LocalUserManager
import com.musashi.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONArray
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
        createDatabaseFromRawJson(context)
        getListOfCountries()
        readLocalSetting()
    }
    /////////////////////////////////////////////////Public functions//////////////////////////////////////////////////////
    fun onBookmarkClick(){

        if(isCityBookmarked()){
            saveLocalSetting("", "", false)
        }else{
            saveLocalSetting(state.value.currentCity.countryName, state.value.currentCity.cityName, true)
        }


    }
    fun isCityBookmarked() = (state.value.isBookmarkSaved) &&
            (state.value.currentCity.countryName == state.value.localCityCountry.first) &&
                (state.value.currentCity.cityName == state.value.localCityCountry.second)

    fun getNextHourWeather(): Double?{
        val currentHour = state.value.weatherStatus?.current?.time?.split(":")?.get(0) + ":00"
        val nextHourIndex = state.value.weatherStatus?.hourly?.time?.indexOf(currentHour)?.plus(1)
        val nextHourTemp = nextHourIndex?.let {
            state.value.weatherStatus?.hourly?.temperature2m?.get(
                it
            )
        }
        return nextHourTemp
    }
    fun getNextHourWeatherCode(): Int?{
        val currentHour = state.value.weatherStatus?.current?.time?.split(":")?.get(0) + ":00"
        val nextHourIndex = state.value.weatherStatus?.hourly?.time?.indexOf(currentHour)
        val nextHourWeatherCode = nextHourIndex?.let {
            state.value.weatherStatus?.hourly?.weatherCode?.get(
                it
            )
        }
        return nextHourWeatherCode
    }

    fun selectCountry(countryName: String){

        viewModelScope.launch {
            if (weatherRepository.getCities(countryName = countryName).first().isNotEmpty()) {
                _state.update { it.copy(isCountrySelected = true) }
                _state.update {
                    it.copy(
                        cities = weatherRepository.getCities(countryName = countryName).first()
                    )
                }
            }else{
                _state.update { it.copy(isCountrySelected = false) }
            }
        }
    }

    fun selectCity(cityName: String, countryName: String){
        viewModelScope.launch {
            weatherRepository.getCity(
                cityName = cityName.replaceFirstChar { char -> char.uppercaseChar() },
                countryName = countryName.replaceFirstChar { char -> char.uppercaseChar() }
            )?.let { targetCity ->
                _state.update {
                    it.copy(
                        currentCity = CityModel(
                            id = targetCity.id,
                            countryName = targetCity.countryName,
                            cityName = targetCity.cityName,
                            latitude = targetCity.latitude,
                            longitude = targetCity.longitude
                        )
                    )
                }
            }?: _state.update {
                it.copy(
                    currentCity = CityModel(
                        id = 0,
                        countryName = "",
                        cityName = "",
                        latitude = 0.0,
                        longitude = 0.0
                    )
                )
            }
            getWeathers()
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
                    isBookmarkSaved = state,
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
                    isBookmarkSaved = bookmarkState
                )
            }
            selectCountry(state.value.localCityCountry.first)
            selectCity(state.value.localCityCountry.second, state.value.localCityCountry.first)
        }
    }

    private fun createDatabaseFromRawJson(context: Context) {

        _state.update { it.copy(isDatabaseLoaded = false) }
        viewModelScope.launch {
            val count = weatherRepository.getTableCount()

            if (count < 47868) {
                val citiesJsonArray: JSONArray =
                    context.resources.openRawResource(R.raw.world_cities_with_id).bufferedReader()
                        .use {
                            JSONArray(it.readText())
                        }
                citiesJsonArray.takeIf { it.length() > 0 }?.let { list ->
                    for (index in 0 until list.length()) {
                        val cityObj = list.getJSONObject(index)
                        weatherRepository.upsertCity(
                            CityModel(
                                id = cityObj.getInt("id"),
                                countryName = cityObj.getString("country"),
                                cityName = cityObj.getString("city_ascii"),
                                latitude = cityObj.getDouble("lat"),
                                longitude = cityObj.getDouble("lng")
                            )
                        )
                    }
                }
            }
            _state.update { it.copy(isDatabaseLoaded = true) }
        }
    }

    private fun getListOfCountries(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    countries = weatherRepository.getCountries().first()
                )
            }
        }
    }

    private fun getWeathers(){
        if( state.value.currentCity != CityModel(0, "", "",0.0, 0.0 )) {

            _state.update { it.copy(isWeatherLoading = true) }

            viewModelScope.launch {
                weatherRepository.getWeathers(
                    latitude = state.value.currentCity.latitude,
                    longitude = state.value.currentCity.longitude,
                ).onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                }.onRight { weathers ->
                    _state.update {
                        it.copy(weatherStatus = weathers)
                    }
                }
            }
            _state.update { it.copy(isWeatherLoading = false) }
        }
    }
}