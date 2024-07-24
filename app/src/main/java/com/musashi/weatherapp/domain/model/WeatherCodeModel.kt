package com.musashi.weatherapp.domain.model

import com.musashi.weatherapp.R

enum class WeatherCodeModel(val imageId: Int, val stringId: Int) {
    ClearSky(R.drawable.clear_sky, R.string.clear_sky),
    MainlyClear(R.drawable.mainly_clear, R.string.mainly_clear),
    Fog(R.drawable.fog, R.string.fog),
    Drizzle(R.drawable.drizzle, R.string.drizzle),
    FreezingDrizzle(R.drawable.freezing_drizzle, R.string.freezing_drizzle),
    Rain(R.drawable.rain, R.string.rain),
    FreezingRain(R.drawable.freezing_rain, R.string.freezing_rain),
    SnowFall(R.drawable.snow_fall, R.string.snow_fall),
    SnowGrain(R.drawable.snow_grain, R.string.snow_grain),
    RainShower(R.drawable.rain_shower, R.string.rain_shower),
    SnowShower(R.drawable.snow_shower, R.string.snow_shower),
    Thunderstorm(R.drawable.thunder, R.string.thunder),
    ThunderStormHail(R.drawable.thunder, R.string.thunder),
}