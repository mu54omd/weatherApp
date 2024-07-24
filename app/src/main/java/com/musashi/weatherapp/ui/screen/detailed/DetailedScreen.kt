package com.musashi.weatherapp.ui.screen.detailed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.ui.helper.returnWeatherCode
import com.musashi.weatherapp.ui.screen.detailed.components.CityDetails
import com.musashi.weatherapp.ui.screen.detailed.components.WeatherDetailsItem
import com.musashi.weatherapp.ui.screen.detailed.components.WeatherDetailsTitle
import com.musashi.weatherapp.ui.screen.summary.WeatherState

@Composable
fun DetailedScreen(
    modifier: Modifier = Modifier,
    state: WeatherState,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CityDetails(
            weatherCodeImage = returnWeatherCode(state.weatherStatus?.current?.weatherCode ?: 0).imageId,
            weatherCodeTitle = returnWeatherCode(state.weatherStatus?.current?.weatherCode ?: 0).stringId,
            cityTitle = state.currentCity.cityName,
            lat = state.currentCity.latitude,
            lng = state.currentCity.longitude
        )
        Spacer(modifier = Modifier.height(10.dp))
        WeatherDetailsTitle()
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn() {
            val currentHour = state.weatherStatus?.current?.time?.split(":")?.get(0) + ":00"
            val nextHourIndex = state.weatherStatus?.hourly?.time?.indexOf(currentHour)?.plus(1)
            state.weatherStatus?.hourly?.let { status ->
                itemsIndexed(status.time){ index, item ->
                    if (index >= nextHourIndex!!) {
                        WeatherDetailsItem(
                            date = item.split("T")[0],
                            time = item.split("T")[1],
                            temp = status.temperature2m[index],
                            humidity = status.relativeHumidity2m[index]
                        )
                    }
                }
            }
        }
    }
}






