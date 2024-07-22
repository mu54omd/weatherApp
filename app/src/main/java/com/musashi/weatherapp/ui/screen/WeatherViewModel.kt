package com.musashi.weatherapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
): ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()

    init {
        getCities()
        moveCitiesFromDb()
    }

    fun getNextHourWeather(): Double?{
        val currentHour = state.value.weatherStatus?.current?.time?.split(":")?.get(0) + ":00"
        val nextHourIndex = state.value.weatherStatus?.hourly?.time?.indexOf(currentHour)
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

    fun changeCity(cityName: String){
        viewModelScope.launch {
            weatherRepository.getCity(cityName = cityName.replaceFirstChar { char -> char.uppercaseChar() })?.let { targetCity ->
                _state.update {
                    it.copy(
                        currentCity = CityModel(
                            cityName = targetCity.cityName,
                            latitude = targetCity.latitude,
                            longitude = targetCity.longitude
                        )
                    )
                }
            }?: _state.update {
                it.copy(
                    currentCity = CityModel(
                        cityName = "",
                        latitude = 0.0,
                        longitude = 0.0
                    )
                )
            }
            getWeathers()
        }
    }

    private fun getCities(){
        viewModelScope.launch {
            _state.update {
                it.copy(isCityLoading = true)
            }
            weatherRepository.getCityCoordinate()
                .onLeft { error ->
                _state.update {
                    it.copy(
                        error = error.error.message,
                        )
                }
            }.onRight { cities ->
                _state.update {
                    it.copy(cities = cities)
                }
                cities.forEach { city ->
                    weatherRepository.upsertCity(city)
                }
            }
            _state.update {
                it.copy(isCityLoading = false)
            }
        }
    }

    private fun moveCitiesFromDb(){
        viewModelScope.launch {
            _state.update {
                it.copy(cities = weatherRepository.getCitiesFromDb().first())
            }
        }
    }

    private fun getWeathers(){

        if( state.value.currentCity.longitude != 0.0 && state.value.currentCity.latitude != 0.0 ) {

            viewModelScope.launch {
                _state.update {
                    it.copy(isWeatherLoading = true)
                }
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
                _state.update {
                    it.copy(isWeatherLoading = false)
                }
            }
        }
    }
}