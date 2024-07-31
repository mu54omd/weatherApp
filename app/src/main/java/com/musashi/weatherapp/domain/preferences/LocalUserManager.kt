package com.musashi.weatherapp.domain.preferences

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveSelectedCountry(country: String)
    suspend fun saveSelectedCity(city: String)
    suspend fun saveBookmarkState(state: Boolean)
    suspend fun saveThemeState(color: String)

    fun readSelectedCountry(): Flow<String>
    fun readSelectedCity(): Flow<String>
    fun readBookmarkState(): Flow<Boolean>
    fun readThemeState(): Flow<String>
}