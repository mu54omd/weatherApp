package com.musashi.weatherapp.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherCurrentResponseModel(
    val current: Current,
    @SerializedName("current_units")
    val currentUnits: CurrentUnits,
    val elevation: Double,
    @SerializedName("generationtime_ms")
    val generationTimeMs: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)