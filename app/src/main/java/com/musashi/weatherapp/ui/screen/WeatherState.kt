package com.musashi.weatherapp.ui.screen

import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.domain.model.WeatherResponseModel

data class WeatherState(
    val isCityLoading: Boolean = false,
    val isWeatherLoading: Boolean = false,
    val currentCity: CityModel = CityModel("", 0.0, 0.0),
    val cities: List<CityModel> = emptyList<CityModel>(),
    val weatherStatus: WeatherResponseModel? = null,
    val error: String? = null
)
