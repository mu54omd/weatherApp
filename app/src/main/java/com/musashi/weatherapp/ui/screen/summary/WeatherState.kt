package com.musashi.weatherapp.ui.screen.summary

import com.musashi.weatherapp.domain.model.BookmarkModel
import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.domain.model.WeatherResponseModel

data class WeatherState(
    val isBookmarkListLoaded: Boolean = false,
    val isSomethingDeleteFromBookmarkList: Boolean = false,
    val isBookmarkWeatherReceived: Boolean = false,

    val isRefreshing: Boolean = false,

    val isDatabaseLoaded: Boolean = false,
    val isWeatherLoading: Boolean = false,
    val isCountrySelected: Boolean = false,
    val localCityCountry: Pair<String, String> = Pair("",""),
    val isDefaultCitySet: Boolean = false,
    val currentCity: CityModel = CityModel(0,"","" ,0.0, 0.0),
    val countries: List<String> = emptyList(),
    val cities: List<CityModel> = emptyList(),
    val weatherStatus: WeatherResponseModel? = null,
    val error: String? = null,
    val bookmarkedCities: List<CityModel> = emptyList(),
    val result: List<BookmarkModel> = emptyList()
)
