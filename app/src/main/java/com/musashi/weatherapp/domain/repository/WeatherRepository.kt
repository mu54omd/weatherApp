package com.musashi.weatherapp.domain.repository

import arrow.core.Either
import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.domain.model.MapApiModel
import com.musashi.weatherapp.domain.model.NetworkError
import com.musashi.weatherapp.domain.model.WeatherCurrentResponseModel
import com.musashi.weatherapp.domain.model.WeatherFullResponseModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {


    //WeatherApi
    suspend fun getFullWeathers(latitude: Double, longitude: Double, forecastDays: Int): Either<NetworkError, WeatherFullResponseModel>
    suspend fun getCurrentWeathers(latitude: Double, longitude: Double): Either<NetworkError, WeatherCurrentResponseModel>

    //MapApi
    suspend fun getLocation(cityName: String, countryName: String): Either<NetworkError, List<MapApiModel>>

    //From CityDao
    suspend fun getTableCount(): Long
    fun getCountries(): Flow<List<String>>
    fun getCountriesFa(): Flow<List<String>>
    fun getCities(countryName: String): Flow<List<CityModel>>
    suspend fun getCity(cityName: String, countryName: String): CityModel?
    suspend fun upsertCity(cityModel: CityModel)

    //From BookmarkDao
    suspend fun upsertBookmark(cityModel: CityModel)
    suspend fun deleteBookmark(cityModel: CityModel)
    fun getBookmark(): Flow<List<CityModel>>

}