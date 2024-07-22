package com.musashi.weatherapp.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Current(
    val interval: Int,
    val rain: Double,
    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: Int,
    @SerializedName("temperature_2m")
    val temperature2m: Double,
    val time: String,
    @SerializedName("weather_code")
    val weatherCode: Int
)