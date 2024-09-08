package com.musashi.weatherapp.domain.model

data class BookmarkModel(
    val cityModel: CityModel,
    val temp: Double,
    val weatherCode: Int,
    val isDay: Int,
    val error: Int?
)