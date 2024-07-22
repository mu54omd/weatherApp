package com.musashi.weatherapp.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hourly(
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,
    val time: List<String>,
    @SerializedName("weather_code")
    val weatherCode: List<Int>
)