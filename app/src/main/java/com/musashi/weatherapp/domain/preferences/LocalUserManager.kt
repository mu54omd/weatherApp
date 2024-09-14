package com.musashi.weatherapp.domain.preferences

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveSelectedCountry(country: String)
    suspend fun saveSelectedCity(city: String)
    suspend fun saveDefaultCityState(state: Boolean)
    suspend fun saveThemeState(color: String)
    suspend fun saveAppLanguage(language: String)
    suspend fun saveForecastDays(forecastDays: Int)

    fun readSelectedCountry(): Flow<String>
    fun readSelectedCity(): Flow<String>
    fun readDefaultCityState(): Flow<Boolean>
    fun readThemeState(): Flow<String>
    fun readAppLanguage(): Flow<String>
    fun readForecastDays(): Flow<Int>
}