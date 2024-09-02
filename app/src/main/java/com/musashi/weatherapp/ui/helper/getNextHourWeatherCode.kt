package com.musashi.weatherapp.ui.helper

import com.musashi.weatherapp.ui.screen.summary.WeatherState

fun getNextHourWeatherCode(state: WeatherState): Int?{
    val currentHour = state.weatherFullStatus?.current?.time?.split(":")?.get(0) + ":00"
    val nextHourIndex = state.weatherFullStatus?.hourly?.time?.indexOf(currentHour)
    val nextHourWeatherCode = nextHourIndex?.let {
        state.weatherFullStatus.hourly.weatherCode[it]
    }
    return nextHourWeatherCode
}