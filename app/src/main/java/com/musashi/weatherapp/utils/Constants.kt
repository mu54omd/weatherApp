package com.musashi.weatherapp.utils

object Constants {
    const val WEATHER_API_BASE_URL = "https://api.open-meteo.com/v1/"
    const val TIME_ZONE = "auto"
    const val HOURLY = "temperature_2m,relative_humidity_2m,apparent_temperature,weather_code,is_day"
    const val CURRENT = "temperature_2m,relative_humidity_2m,apparent_temperature,weather_code,is_day"
    const val FORECAST_DAYS = "forecast_days"
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
    val TIME_EN = listOf(
        "00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00",
        "13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"
    )
    val TIME_FA = listOf(
        "۰۰:۰۰","۰۱:۰۰","۰۲:۰۰","۰۳:۰۰","۰۴:۰۰","۰۵:۰۰","۰۶:۰۰","۰۷:۰۰","۰۸:۰۰","۰۹:۰۰","۱۰:۰۰","۱۱:۰۰","۱۲:۰۰","۱۳:۰۰","۱۴:۰۰","۱۵:۰۰","۱۶:۰۰","۱۷:۰۰","۱۸:۰۰","۱۹:۰۰","۲۰:۰۰","۲۱:۰۰","۲۲:۰۰","۲۳:۰۰",
    )

    val forecastMenuEn = listOf("3", "7", "14")
    val forecastMenuFa = listOf("۳", "۷", "۱۴")


}