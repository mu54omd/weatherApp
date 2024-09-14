package com.musashi.weatherapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherCurrentResponseModel(
    val current: Current,
    val latitude: Double,
    val longitude: Double,
)