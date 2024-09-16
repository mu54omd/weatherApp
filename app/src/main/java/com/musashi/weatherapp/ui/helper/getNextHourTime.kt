package com.musashi.weatherapp.ui.helper

import com.musashi.weatherapp.ui.screen.WeatherState

fun getNextHourTime(state: WeatherState): Int?{
    val nextHourIndex = getNextHourIndex(state)
    val isNextHourDay = nextHourIndex?.let { state.weatherFullStatus?.hourly?.isDay?.get(it) }
    return isNextHourDay
}