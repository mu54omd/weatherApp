package com.musashi.weatherapp.data.remote

import com.musashi.weatherapp.domain.model.MapApiModel
import com.musashi.weatherapp.utils.Constants.ADDRESS_DETAILS
import com.musashi.weatherapp.utils.Constants.FORMAT
import com.musashi.weatherapp.utils.Constants.LIMIT
import retrofit2.http.GET
import retrofit2.http.Query

interface MapApi {
    @GET("search?")
    suspend fun getLocation(
        @Query("addressdetails") addressDetails: Int = ADDRESS_DETAILS,
        @Query("country") country: String,
        @Query("city") city: String,
        @Query("format") format: String = FORMAT,
        @Query("limit") limit: Int = LIMIT,
    ): List<MapApiModel>
}