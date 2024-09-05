package com.musashi.weatherapp.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Hourly(
    @SerializedName("apparent_temperature")
    val apparentTemperature: List<Double>,
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,
    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: List<Int>,
    val time: List<String>,
    @SerializedName("weather_code")
    val weatherCode: List<Int>,
    @SerializedName("is_day")
    val isDay: List<Int>
)