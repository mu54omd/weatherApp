package com.musashi.weatherapp.ui.screen.summary

import com.musashi.weatherapp.domain.model.BookmarkModel
import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.domain.model.EmptyCity
import com.musashi.weatherapp.domain.model.MapApiModel
import com.musashi.weatherapp.domain.model.WeatherCurrentResponseModel
import com.musashi.weatherapp.domain.model.WeatherFullResponseModel

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
    val currentCity: CityModel = EmptyCity.emptyCity,
    val countries: List<String> = emptyList(),
    val countriesFa: List<String> = emptyList(),
    val cities: List<CityModel> = emptyList(),
    val weatherFullStatus: WeatherFullResponseModel? = null,
    val weatherCurrentStatus: WeatherCurrentResponseModel? = null,
    val errorCurrent: Int? = null,
    val errorHourly: Int? = null,
    val bookmarkedCities: List<CityModel> = emptyList(),
    val result: List<BookmarkModel> = emptyList(),
    val forecastDays: Int = 3,

    //state related to map api
    val mapApiResult: List<MapApiModel> = emptyList(),
    val mapApiError: String? = null,
)
