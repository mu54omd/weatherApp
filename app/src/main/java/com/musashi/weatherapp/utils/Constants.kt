package com.musashi.weatherapp.utils

object Constants {
    const val BASE_URL = "https://api.open-meteo.com/v1/"
    const val TIME_ZONE = "auto"
    const val HOURLY = "temperature_2m,relative_humidity_2m,weather_code"
    const val CURRENT = "temperature_2m,relative_humidity_2m,rain,weather_code"
    const val FORECAST_DAYS = 3
    const val CITY_DATABASE_NAME = "city_db"

    const val USER_SETTINGS = "userSettings"
    const val SELECTED_CITY = "selectedCity"
    const val SELECTED_COUNTRY = "selectedCOUNTRY"
    const val IS_BOOKMARKED = "isBookmarked"
}