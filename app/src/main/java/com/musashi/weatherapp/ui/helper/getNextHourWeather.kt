package com.musashi.weatherapp.ui.helper

import com.musashi.weatherapp.ui.screen.summary.WeatherState

fun getNextHourWeather(state: WeatherState): Double?{
    val currentHour = state.weatherStatus?.current?.time?.split(":")?.get(0) + ":00"
    val nextHourIndex = state.weatherStatus?.hourly?.time?.indexOf(currentHour)?.plus(1)
    val nextHourTemp = nextHourIndex?.let {
        state.weatherStatus.hourly.temperature2m[it]
    }
    return nextHourTemp
}