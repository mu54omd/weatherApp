package com.musashi.weatherapp.ui.helper

import com.musashi.weatherapp.ui.screen.summary.WeatherState

fun getNextHourWeatherCode(state: WeatherState): Int?{
    val nextHourIndex = getNextHourIndex(state)
    val nextHourWeatherCode = nextHourIndex?.let {
        state.weatherFullStatus?.hourly?.weatherCode?.get(
            it
        )
    }
    return nextHourWeatherCode
}