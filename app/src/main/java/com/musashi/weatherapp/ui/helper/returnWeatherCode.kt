package com.musashi.weatherapp.ui.helper

import com.musashi.weatherapp.domain.model.WeatherCodeModel

fun returnWeatherCode(weatherCode: Int): WeatherCodeModel{
    val codeModel = when(weatherCode){
        0 -> WeatherCodeModel.ClearSky
        1, 2, 3 -> WeatherCodeModel.MainlyClear
        45, 48 -> WeatherCodeModel.Fog
        51, 53, 55 -> WeatherCodeModel.Drizzle
        56, 57 -> WeatherCodeModel.FreezingDrizzle
        61, 63, 65 -> WeatherCodeModel.Rain
        66, 67 -> WeatherCodeModel.FreezingRain
        71, 73, 75 -> WeatherCodeModel.SnowFall
        77 -> WeatherCodeModel.SnowGrain
        80, 81, 82 -> WeatherCodeModel.RainShower
        85, 86 -> WeatherCodeModel.SnowShower
        95 -> WeatherCodeModel.Thunderstorm
        96, 99 -> WeatherCodeModel.ThunderStormHail
        else -> WeatherCodeModel.ClearSky
        }
    return codeModel
}