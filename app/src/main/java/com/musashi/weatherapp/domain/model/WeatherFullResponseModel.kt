package com.musashi.weatherapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherFullResponseModel(
    val current: Current,
    val hourly: Hourly,
    val latitude: Double,
    val longitude: Double,
)