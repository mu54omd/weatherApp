package com.musashi.weatherapp.data.repository

import arrow.core.Either
import com.musashi.weatherapp.data.local.BookmarkDao
import com.musashi.weatherapp.data.local.CityDao
import com.musashi.weatherapp.data.mapper.toNetworkError
import com.musashi.weatherapp.data.remote.MapApi
import com.musashi.weatherapp.data.remote.WeatherApi
import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.domain.model.MapApiModel
import com.musashi.weatherapp.domain.model.NetworkError
import com.musashi.weatherapp.domain.model.WeatherCurrentResponseModel
import com.musashi.weatherapp.domain.model.WeatherFullResponseModel
import com.musashi.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val mapApi: MapApi,
    private val cityDao: CityDao,
    private val bookmarkDao: BookmarkDao
): WeatherRepository {

    override suspend fun getFullWeathers(
        latitude: Double,
        longitude: Double,
        forecastDays: Int,
    ): Either<NetworkError, WeatherFullResponseModel> {
        return Either.catch {
            weatherApi.getFullWeathers(latitude = latitude, longitude = longitude, forecastDays = forecastDays)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getCurrentWeathers(
        latitude: Double,
        longitude: Double
    ): Either<NetworkError, WeatherCurrentResponseModel> {
        return Either.catch {
            weatherApi.getCurrentWeathers(latitude = latitude, longitude = longitude)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    override suspend fun getLocation(
        cityName: String,
        countryName: String
    ): Either<NetworkError, List<MapApiModel>> {
        return Either.catch {
            mapApi.getLocation(city = cityName, country = countryName)
        }.mapLeft {
            it.toNetworkError()
        }
    }

    //For city database
    override suspend fun getTableCount(): Long {
        return cityDao.getTableCount()
    }

    override fun getCountries(): Flow<List<String>> {
        return cityDao.getCountries()
    }

    override fun getCountriesFa(): Flow<List<String>> {
        return cityDao.getCountriesFa()
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


    //For bookmark database
    override suspend fun upsertBookmark(cityModel: CityModel) {
        bookmarkDao.upsert(cityModel)
    }

    override suspend fun deleteBookmark(cityModel: CityModel) {
        bookmarkDao.delete(cityModel)
    }

    override fun getBookmark(): Flow<List<CityModel>> {
        return bookmarkDao.getBookmarkedCities()
    }
}