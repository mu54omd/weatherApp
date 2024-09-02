package com.musashi.weatherapp.ui.screen.detailed.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.ui.helper.returnWeatherCode
import com.musashi.weatherapp.ui.screen.summary.WeatherState

@Composable
fun WeatherDetailsItemList(
    modifier: Modifier = Modifier,
    state: WeatherState,
    dayConditionStart: Int,
    dayConditionEnd: Int,
    currentIndex: Int = 1000,

) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        state = rememberLazyListState(initialFirstVisibleItemIndex = if(currentIndex != 1000) currentIndex else 0)
    ) {
        state.weatherFullStatus?.hourly?.let { status ->
            itemsIndexed(status.time) { index, item ->
                if(index in dayConditionStart..dayConditionEnd){
                    WeatherDetailsItemCard(
                        time = item.split("T")[1],
                        weatherIcon = returnWeatherCode(status.weatherCode[index]).imageId,
                        temperature = status.temperature2m[index],
                        humidity = status.relativeHumidity2m[index]
                    )
                }
            }
        }
    }
}