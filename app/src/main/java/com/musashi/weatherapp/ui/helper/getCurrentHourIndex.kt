package com.musashi.weatherapp.ui.helper

import com.musashi.weatherapp.ui.screen.WeatherState

fun getCurrentHourIndex(state: WeatherState): Int {
    val currentHour = state.weatherFullStatus?.current?.time?.split(":")?.get(0) + ":00"
    val currentHourIndex = currentHour.let { state.weatherFullStatus?.hourly?.time?.indexOf(it) } ?: 0
    return currentHourIndex
}