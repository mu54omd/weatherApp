package com.musashi.weatherapp.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class HourlyUnits(
    @SerializedName("apparent_temperature")
    val apparentTemperature: String,
    @SerializedName("temperature_2m")
    val temperature2m: String,
    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: String,
    val time: String,
    @SerializedName("weather_code")
    val weatherCode: String,
    @SerializedName("is_day")
    val isDay: String
)