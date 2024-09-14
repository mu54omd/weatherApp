package com.musashi.weatherapp.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.musashi.weatherapp.domain.preferences.LocalUserManager
import com.musashi.weatherapp.utils.Constants
import com.musashi.weatherapp.utils.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context
):LocalUserManager {
    override suspend fun saveSelectedCountry(country: String) {
        context.dataStore.edit { setting ->
            setting[PreferencesKeys.SELECTED_COUNTRY] = country
        }
    }
    override suspend fun saveSelectedCity(city: String) {
        context.dataStore.edit { setting ->
            setting[PreferencesKeys.SELECTED_CITY] = city
        }
    }

    override suspend fun saveDefaultCityState(state: Boolean) {
        context.dataStore.edit { setting ->
            setting[PreferencesKeys.Is_DEFAULT_CITY_SAVED] = state
        }
    }

    override suspend fun saveThemeState(color: String) {
        context.dataStore.edit { setting ->
            setting[PreferencesKeys.THEME_COLOR] = color
        }
    }

    override suspend fun saveAppLanguage(language: String) {
        context.dataStore.edit { setting ->
            setting[PreferencesKeys.APP_LANGUAGE] = language
        }
    }

    override suspend fun saveForecastDays(forecastDays: Int) {
        context.dataStore.edit { setting ->
            setting[PreferencesKeys.FORECAST_DAYS] = forecastDays
        }
    }

    override fun readSelectedCountry(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.SELECTED_COUNTRY]?:""
        }
    }

    override fun readSelectedCity(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.SELECTED_CITY]?:""
        }
    }

    override fun readDefaultCityState(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.Is_DEFAULT_CITY_SAVED]?:false
        }
    }

    override fun readThemeState(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.THEME_COLOR]?:"Light"
        }
    }

    override fun readAppLanguage(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_LANGUAGE] ?: "en-US"
        }
    }

    override fun readForecastDays(): Flow<Int> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.FORECAST_DAYS] ?: 3
        }
    }

}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)
private object PreferencesKeys {
    val SELECTED_CITY = stringPreferencesKey(name = Constants.SELECTED_CITY)
    val SELECTED_COUNTRY = stringPreferencesKey(name = Constants.SELECTED_COUNTRY)
    val Is_DEFAULT_CITY_SAVED = booleanPreferencesKey(name = Constants.IS_DEFAULT_CITY_SAVED)
    val THEME_COLOR = stringPreferencesKey(name = Constants.THEME_COLOR)
    val APP_LANGUAGE = stringPreferencesKey(name = Constants.APP_LANGUAGE)
    val FORECAST_DAYS = intPreferencesKey(name = Constants.FORECAST_DAYS)
}