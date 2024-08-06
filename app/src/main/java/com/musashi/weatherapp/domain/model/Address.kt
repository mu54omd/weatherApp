package com.musashi.weatherapp.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerializedName("country")
    val country: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("county")
    val county: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("village")
    val village: String
)