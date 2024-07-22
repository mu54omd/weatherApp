package com.musashi.weatherapp.ui.helper

import com.musashi.weatherapp.domain.model.WeatherCodeModel

fun returnWeatherCode(weatherCode: Int): Int{
    val id = when(weatherCode){
        0 -> WeatherCodeModel.ClearSky.id
        1, 2, 3 -> WeatherCodeModel.MainlyClear.id
        45, 48 -> WeatherCodeModel.Fog.id
        51, 53, 55 -> WeatherCodeModel.Drizzle.id
        56, 57 -> WeatherCodeModel.FreezingDrizzle.id
        61, 63, 65 -> WeatherCodeModel.Rain.id
        66, 67 -> WeatherCodeModel.FreezingRain.id
        71, 73, 75 -> WeatherCodeModel.SnowFall.id
        77 -> WeatherCodeModel.SnowGrain.id
        80, 81, 82 -> WeatherCodeModel.RainShower.id
        85, 86 -> WeatherCodeModel.SnowShower.id
        95 -> WeatherCodeModel.Thunderstorm.id
        96, 99 -> WeatherCodeModel.ThunderStormHail.id
        else -> WeatherCodeModel.ClearSky.id
    }
    return id
}