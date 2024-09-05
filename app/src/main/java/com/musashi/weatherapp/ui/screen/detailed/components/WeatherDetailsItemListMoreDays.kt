package com.musashi.weatherapp.ui.screen.detailed.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.musashi.weatherapp.ui.helper.returnWeatherCode
import com.musashi.weatherapp.ui.screen.summary.WeatherState

@Composable
fun WeatherDetailsItemListMoreDays(
    dateText: String,
    modifier: Modifier = Modifier,
    state: WeatherState,
    dayConditionStart: Int,
    dayConditionEnd: Int,
    currentIndex: Int = 1000,

) {
    var isLazyRowVisible by rememberSaveable { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        TextButton(
            onClick = { isLazyRowVisible = !isLazyRowVisible },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        ) {
            Text(
                text = dateText,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        AnimatedVisibility(visible = isLazyRowVisible) {
            LazyColumn(
                modifier = Modifier
                    .height(150.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
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
    }
}