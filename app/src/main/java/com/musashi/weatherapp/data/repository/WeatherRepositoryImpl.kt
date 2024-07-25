package com.musashi.weatherapp.data.repository

import arrow.core.Either
import com.musashi.weatherapp.data.local.CityDao
import com.musashi.weatherapp.data.mapper.toNetworkError
import com.musashi.weatherapp.data.remote.WeatherApi
import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.domain.model.NetworkError
import com.musashi.weatherapp.domain.model.WeatherResponseModel
import com.musashi.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val cityDao: CityDao
): WeatherRepository {

    override suspend fun getWeathers(latitude: Double, longitude: Double): Either<NetworkError, WeatherResponseModel> {
        return Either.catch {
            weatherApi.getWeathers(latitude = latitude, longitude = longitude)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getTableCount(): Long {
        return cityDao.getTableCount()
    }

    override fun getCountries(): Flow<List<String>> {
        return cityDao.getCountries()
    }

    override fun getCities(countryName: String): Flow<List<CityModel>> {
        return cityDao.selectCountry(countryName)
    }

    override suspend fun upsertCity(cityModel: CityModel) {
        cityDao.upsert(cityModel)
    }

    override suspend fun getCity(cityName: String, countryName: String): CityModel? {
        return cityDao.selectCity(cityName, countryName)
    }

}