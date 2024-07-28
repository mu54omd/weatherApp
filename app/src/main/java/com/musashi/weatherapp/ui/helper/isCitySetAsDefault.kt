package com.musashi.weatherapp.ui.helper

import com.musashi.weatherapp.ui.screen.summary.WeatherState

fun isCitySetAsDefault(state: WeatherState) = (state.isDefaultCitySet) &&
        (state.currentCity.countryName == state.localCityCountry.first) &&
        (state.currentCity.cityName == state.localCityCountry.second)