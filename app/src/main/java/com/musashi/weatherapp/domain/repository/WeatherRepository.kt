package com.musashi.weatherapp.domain.repository

import arrow.core.Either
import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.domain.model.NetworkError
import com.musashi.weatherapp.domain.model.WeatherResponseModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getCityCoordinate(): Either<NetworkError, List<CityModel>>
    suspend fun getWeathers(latitude: Double, longitude: Double): Either<NetworkError, WeatherResponseModel>
    fun getCitiesFromDb(): Flow<List<CityModel>>
    suspend fun getCity(cityName: String): CityModel?
    suspend fun upsertCity(cityModel: CityModel)

}