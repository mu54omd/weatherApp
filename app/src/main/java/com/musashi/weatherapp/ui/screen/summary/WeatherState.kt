package com.musashi.weatherapp.ui.screen.summary

import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.domain.model.WeatherResponseModel

data class WeatherState(
    val isDatabaseLoaded: Boolean = false,
    val isWeatherLoading: Boolean = false,
    val isCountrySelected: Boolean = false,
    val isCityListLoading: Boolean = false,
    val currentCity: CityModel = CityModel(0,"","" ,0.0, 0.0),
    val countries: List<String> = emptyList(),
    val cities: List<CityModel> = emptyList<CityModel>(),
    val weatherStatus: WeatherResponseModel? = null,
    val error: String? = null
)
