package com.musashi.weatherapp.ui.helper

import com.musashi.weatherapp.domain.model.WeatherCodeModel

fun returnWeatherCode(weatherCode: Int, isDay: Int): WeatherCodeModel{
    val codeModel = when(weatherCode){
        0 -> if(isDay == 1) WeatherCodeModel.ClearSky else WeatherCodeModel.ClearSkyNight
        1 -> if(isDay == 1) WeatherCodeModel.MainlyClear else WeatherCodeModel.MainlyClearNight
        2 -> if(isDay == 1) WeatherCodeModel.PartlyCloudy else WeatherCodeModel.PartlyCloudyNight
        3 -> WeatherCodeModel.Overcast
        45 -> WeatherCodeModel.Fog
        48 -> WeatherCodeModel.FogDepositingRime
        51 -> WeatherCodeModel.DrizzleLight
        53 -> WeatherCodeModel.DrizzleModerate
        55 -> WeatherCodeModel.DrizzleDense
        56 -> WeatherCodeModel.FreezingDrizzleLight
        57 -> WeatherCodeModel.FreezingDrizzleDense
        61 -> WeatherCodeModel.RainSlight
        63 -> WeatherCodeModel.RainModerate
        65 -> WeatherCodeModel.RainHeavy
        66 -> WeatherCodeModel.FreezingRainLight
        67 -> WeatherCodeModel.FreezingRainHeavy
        71 -> WeatherCodeModel.SnowFallSlight
        73 -> WeatherCodeModel.SnowFallModerate
        75 -> WeatherCodeModel.SnowFallHeavy
        77 -> WeatherCodeModel.SnowGrain
        80 -> WeatherCodeModel.RainShowerSlight
        81 -> WeatherCodeModel.RainShowerModerate
        82 -> WeatherCodeModel.RainShowerViolent
        85 -> WeatherCodeModel.SnowShowerSlight
        86 -> WeatherCodeModel.SnowShowerHeavy
        95 -> WeatherCodeModel.ThunderstormSlight
        96 -> WeatherCodeModel.ThunderStormSlightHail
        99 -> WeatherCodeModel.ThunderStormHeavyHail
        else -> WeatherCodeModel.ClearSky
        }
    return codeModel
}