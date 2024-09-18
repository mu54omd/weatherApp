package com.musashi.weatherapp.ui.screen.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.ui.helper.returnWeatherCode
import com.musashi.weatherapp.ui.screen.WeatherState

@Composable
fun WeatherDetailsItemListMoreDays(
    state: WeatherState,
    dayConditionStart: Int,
    dayConditionEnd: Int,
    currentIndex: Int = 1000,

    ) {
    LazyRow(
        modifier = Modifier.width(300.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        state = rememberLazyListState(initialFirstVisibleItemIndex = if (currentIndex != 1000) currentIndex else 0)
    ) {
        state.weatherFullStatus?.hourly?.let { status ->
            itemsIndexed(status.time) { index, item ->
                if (index in dayConditionStart..dayConditionEnd) {
                    WeatherDetailsItemMoreDaysCard(
                        time = item.split("T")[1],
                        weatherIcon = returnWeatherCode(status.weatherCode[index], status.isDay[index]).imageId,
                        temperature = status.temperature2m[index],
                    )
                }
            }
        }
    }
}