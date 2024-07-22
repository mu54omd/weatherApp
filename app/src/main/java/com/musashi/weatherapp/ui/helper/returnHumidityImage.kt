package com.musashi.weatherapp.ui.helper

import com.musashi.weatherapp.R

fun returnHumidityImage(humidity: Int):Int{
    val id = when(humidity){
        in 1 until 25 -> R.drawable.humidity_very_low
        in 25 until 50 -> R.drawable.humidity_low
        in 50 until 75 -> R.drawable.humidity_medium
        in 75 until 100 -> R.drawable.humidity_high
        else -> R.drawable.humidity_very_low
    }
    return id
}