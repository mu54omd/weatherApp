package com.musashi.weatherapp.ui.screen.detailed.components

import androidx.compose.runtime.Composable
import com.musashi.weatherapp.ui.screen.summary.WeatherState

@Composable
fun NextDayWeatherSelector(
    state: WeatherState,
    selectedDay: Int,
) {
    when (selectedDay) {
        0 -> WeatherDetailsItemListMoreDays(
            state = state,
            dayConditionStart = 72,
            dayConditionEnd = 95,
        )

        1 -> WeatherDetailsItemListMoreDays(
            state = state,
            dayConditionStart = 96,
            dayConditionEnd = 119,
        )

        2 -> WeatherDetailsItemListMoreDays(
            state = state,
            dayConditionStart = 120,
            dayConditionEnd = 143,
        )

        3 -> WeatherDetailsItemListMoreDays(
            state = state,
            dayConditionStart = 144,
            dayConditionEnd = 167,
        )

        4 -> WeatherDetailsItemListMoreDays(
            state = state,
            dayConditionStart = 168,
            dayConditionEnd = 191,
        )
        5 -> WeatherDetailsItemListMoreDays(
            state = state,
            dayConditionStart = 192,
            dayConditionEnd = 215,
        )
        6 -> WeatherDetailsItemListMoreDays(
            state = state,
            dayConditionStart = 216,
            dayConditionEnd = 239,
        )
        7 -> WeatherDetailsItemListMoreDays(
            state = state,
            dayConditionStart = 240,
            dayConditionEnd = 263,
        )
        8 -> WeatherDetailsItemListMoreDays(
            state = state,
            dayConditionStart = 264,
            dayConditionEnd = 287,
        )
        9 -> WeatherDetailsItemListMoreDays(
            state = state,
            dayConditionStart = 288,
            dayConditionEnd = 311,
        )
        10 -> WeatherDetailsItemListMoreDays(
            state = state,
            dayConditionStart = 312,
            dayConditionEnd = 335,
        )
    }

}