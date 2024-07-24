package com.musashi.weatherapp.ui.screen.detailed

import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.domain.model.WeatherResponseModel

data class DetailedState(
    val currentCity: CityModel = CityModel("", 0.0, 0.0),
    val weatherStatus: WeatherResponseModel? = null,
    val error: String? = null
)