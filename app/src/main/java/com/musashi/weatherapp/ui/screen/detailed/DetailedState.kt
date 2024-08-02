package com.musashi.weatherapp.ui.screen.detailed

import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.domain.model.EmptyCity
import com.musashi.weatherapp.domain.model.WeatherResponseModel

data class DetailedState(
    val currentCity: CityModel = EmptyCity.emptyCity,
    val weatherStatus: WeatherResponseModel? = null,
    val error: String? = null
)