package com.musashi.weatherapp.ui.screen.detailed

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOff
import androidx.compose.material.icons.filled.SignalWifiStatusbarConnectedNoInternet4
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.common.EmptyScreen
import com.musashi.weatherapp.ui.common.WeatherLineChart
import com.musashi.weatherapp.ui.helper.returnWeatherCode
import com.musashi.weatherapp.ui.screen.detailed.components.CityDetails
import com.musashi.weatherapp.ui.screen.detailed.components.WeatherDetailsItemList
import com.musashi.weatherapp.ui.screen.detailed.components.WeatherDetailsTitle
import com.musashi.weatherapp.ui.screen.summary.WeatherState

@Composable
fun DetailedScreen(
    modifier: Modifier = Modifier,
    state: WeatherState,
    onBookmarkClick: () -> Unit,
    isCitySetAsDefault: () -> Boolean,
    isErrorOccurred: Boolean
) {
    var isExpanded1 by rememberSaveable { mutableStateOf(true) }
    var isExpanded2 by rememberSaveable { mutableStateOf(false) }
    var isExpanded3 by rememberSaveable { mutableStateOf(false) }
    var isExpanded4 by rememberSaveable { mutableStateOf(false) }
    val times = mutableListOf<String>()
    state.weatherStatus?.hourly?.time?.forEach {
        times += it.split("T")[1].split(":")[0]
    }

    if(state.currentCity.cityName != "") {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {

            CityDetails(
                weatherCodeImage = returnWeatherCode(state.weatherStatus?.current?.weatherCode ?: 0).imageId,
                weatherCodeTitle = returnWeatherCode(state.weatherStatus?.current?.weatherCode ?: 0).stringId,
                cityTitle = if(LocalLayoutDirection.current == LayoutDirection.Ltr) { state.currentCity.cityName } else{ state.currentCity.cityNameFa }?: state.currentCity.cityName,
                lat = state.currentCity.latitude,
                lng = state.currentCity.longitude,
                onFavoriteClick = {
                    onBookmarkClick()
                },
                isCitySetAsDefault = state.isDefaultCitySet && isCitySetAsDefault(),
                modifier = Modifier.verticalScroll(rememberScrollState())
            )

            Spacer(modifier = Modifier.height(20.dp))
            if(!isErrorOccurred) {
                val currentHour = state.weatherStatus?.current?.time?.split(":")?.get(0) + ":00"
                val currentIndex = state.weatherStatus?.hourly?.time?.indexOf(currentHour)
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(start = 5.dp, end = 5.dp)
                    ,

                ) {
                    WeatherDetailsTitle(
                        time = stringResource(R.string.today),
                        isExpanded = isExpanded1,
                        onTitleClick = {
                            isExpanded1 = !isExpanded1
                        }
                    )
                    AnimatedVisibility(visible = isExpanded1) {
                        Spacer(modifier = Modifier.height(5.dp))
                        if (currentIndex != null) {
                            WeatherDetailsItemList(
                                state = state,
                                dayConditionStart = 0,
                                dayConditionEnd = 23,
                                currentIndex = currentIndex
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    WeatherDetailsTitle(
                        time = stringResource(R.string.tomorrow),
                        isExpanded = isExpanded2,
                        onTitleClick = {
                            isExpanded2 = !isExpanded2
                        }
                    )
                    AnimatedVisibility(visible = isExpanded2) {
                        Spacer(modifier = Modifier.height(5.dp))
                        WeatherDetailsItemList(
                            state = state,
                            dayConditionStart = 24,
                            dayConditionEnd = 47,
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    WeatherDetailsTitle(
                        time = stringResource(R.string.theـdayـafterـtomorrow),
                        isExpanded = isExpanded3,
                        onTitleClick = {
                            isExpanded3 = !isExpanded3
                        }
                    )
                    AnimatedVisibility(visible = isExpanded3) {
                        Spacer(modifier = Modifier.height(5.dp))
                        WeatherDetailsItemList(
                            state = state,
                            dayConditionStart = 48,
                            dayConditionEnd = 71,
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    WeatherDetailsTitle(
                        time = stringResource(R.string.charts),
                        isExpanded = isExpanded4,
                        onTitleClick = {
                            isExpanded4 = !isExpanded4
                        }
                    )
                    AnimatedVisibility(visible = isExpanded4) {
                        Spacer(modifier = Modifier.height(5.dp))
                        state.weatherStatus?.hourly?.let { hourlyStatus ->
                            WeatherLineChart(
                                xValues = times.subList(0,24),
                                yValues = hourlyStatus.temperature2m,
                                zValues = hourlyStatus.relativeHumidity2m.map { it.toDouble() },
                                wValues = hourlyStatus.apparentTemperature
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                }

            }else{
                EmptyScreen(
                    messageText = state.error.toString(),
                    messageImage = Icons.Default.SignalWifiStatusbarConnectedNoInternet4
                    )
            }
        }
    }else{
        EmptyScreen(
            messageText = stringResource(R.string.no_city_selected),
            messageImage = Icons.Default.LocationOff
            )
    }

}






