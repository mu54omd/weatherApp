package com.musashi.weatherapp.utils

object Constants {
    const val BASE_URL = "https://api.open-meteo.com/v1/"
    const val BASE_URL_CITY_API = "https://cityapi-alpha-default-rtdb.asia-southeast1.firebasedatabase.app/"
//    const val BASE_URL_CITY_API = "http://omdapicode.mooo.com/"
    const val TIME_ZONE = "auto"
    const val HOURLY = "temperature_2m,relative_humidity_2m,weather_code"
    const val CURRENT = "temperature_2m,relative_humidity_2m,rain,weather_code"
    const val FORECAST_DAYS = 3
    const val CITY_DATABASE_NAME = "city_db"
}