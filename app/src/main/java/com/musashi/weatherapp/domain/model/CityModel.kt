package com.musashi.weatherapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityModel(
    @PrimaryKey
    val id: Int,
    val countryName: String,
    val countryNameFa: String,
    val cityName: String,
    val cityNameFa: String?,
    val latitude: Double,
    val longitude: Double
)

object EmptyCity{
//    val emptyCity = CityModel(0,"","",0.0,0.0)
    val emptyCity = CityModel(0,"","","", "",0.0,0.0)
}