package com.musashi.weatherapp.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentUnits(
    @SerializedName("apparent_temperature")
    val apparentTemperature: String,
    val interval: String,
    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: String,
    @SerializedName("temperature_2m")
    val temperature2m: String,
    val time: String,
    @SerializedName("weather_code")
    val weatherCode: String
)