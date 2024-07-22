package com.musashi.weatherapp.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class HourlyUnits(
    @SerializedName("temperature_2m")
    val temperature2m: String,
    val time: String,
    @SerializedName("weather_code")
    val weatherCode: String
)