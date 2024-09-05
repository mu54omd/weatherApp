package com.musashi.weatherapp.ui.helper

import com.musashi.weatherapp.ui.screen.summary.WeatherState

fun getNextHourWeather(state: WeatherState): Double?{

    val nextHourIndex = getNextHourIndex(state)
    val nextHourTemp = nextHourIndex?.let { state.weatherFullStatus?.hourly?.temperature2m?.get(it) }

    return nextHourTemp
}