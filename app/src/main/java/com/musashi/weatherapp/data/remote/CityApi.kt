package com.musashi.weatherapp.data.remote

import com.musashi.weatherapp.domain.model.CityModel
import retrofit2.http.GET

interface CityApi {
    @GET("cities.json")
    suspend fun getCities(): List<CityModel>
}