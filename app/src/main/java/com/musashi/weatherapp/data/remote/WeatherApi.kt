package com.musashi.weatherapp.data.remote

import com.musashi.weatherapp.domain.model.WeatherCurrentResponseModel
import com.musashi.weatherapp.domain.model.WeatherFullResponseModel
import com.musashi.weatherapp.utils.Constants.CURRENT
import com.musashi.weatherapp.utils.Constants.FORECAST_DAYS
import com.musashi.weatherapp.utils.Constants.HOURLY
import com.musashi.weatherapp.utils.Constants.TIME_ZONE
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast?")
    suspend fun getFullWeathers(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = HOURLY,
        @Query("current") current: String = CURRENT,
        @Query("timezone") timezone: String = TIME_ZONE,
        @Query("forecast_days") forecastDays: Int = FORECAST_DAYS
    ): WeatherFullResponseModel

    @GET("forecast?")
    suspend fun getCurrentWeathers(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = CURRENT,
        @Query("timezone") timezone: String = TIME_ZONE,
    ): WeatherCurrentResponseModel
}