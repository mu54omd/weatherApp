package com.musashi.weatherapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class CityModel(
    @PrimaryKey
    val id: Int,
    @SerializedName("country")
    val countryName: String,
    @SerializedName("city_ascii")
    val cityName: String,
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lng")
    val longitude: Double
)