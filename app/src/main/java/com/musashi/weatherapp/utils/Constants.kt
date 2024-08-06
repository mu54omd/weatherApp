package com.musashi.weatherapp.utils

object Constants {
    const val WEATHER_API_BASE_URL = "https://api.open-meteo.com/v1/"
    const val TIME_ZONE = "auto"
    const val HOURLY = "temperature_2m,relative_humidity_2m,weather_code"
    const val CURRENT = "temperature_2m,relative_humidity_2m,rain,weather_code"
    const val FORECAST_DAYS = 3
    //const val CITY_DATABASE_NAME = "city_db"
    const val CITY_BOOKMARK_DATABASE_NAME = "city_bm_db"

    const val MAP_API_BASE_URL = "https://nominatim.openstreetmap.org/"
    const val FORMAT = "json"
    const val LIMIT = 5
    const val ADDRESS_DETAILS = 1

    const val USER_SETTINGS = "userSettings"
    const val SELECTED_CITY = "selectedCity"
    const val SELECTED_COUNTRY = "selectedCOUNTRY"
    const val IS_BOOKMARKED = "isBookmarked"


    const val THEME_COLOR = "themeColor"
    const val APP_LANGUAGE = "appLanguage"

    const val NOTIFICATION_CHANNEL_ID = "notification_channel_id"
    const val NOTIFICATION_ID = 1
    const val NOTIFICATION_NAME = "Notification Name"

}