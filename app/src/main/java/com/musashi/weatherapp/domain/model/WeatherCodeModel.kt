package com.musashi.weatherapp.domain.model

import com.musashi.weatherapp.R

enum class WeatherCodeModel(val id: Int) {
    ClearSky(R.drawable.clear_sky),
    MainlyClear(R.drawable.mainly_clear),
    Fog(R.drawable.fog),
    Drizzle(R.drawable.drizzle),
    FreezingDrizzle(R.drawable.freezing_drizzle),
    Rain(R.drawable.rain),
    FreezingRain(R.drawable.freezing_rain),
    SnowFall(R.drawable.snow_fall),
    SnowGrain(R.drawable.snow_grain),
    RainShower(R.drawable.rain_shower),
    SnowShower(R.drawable.snow_shower),
    Thunderstorm(R.drawable.thunder),
    ThunderStormHail(R.drawable.thunder),
}