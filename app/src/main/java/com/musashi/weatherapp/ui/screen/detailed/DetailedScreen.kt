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
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.common.EmptyScreen
import com.musashi.weatherapp.ui.common.WeatherLineChart
import com.musashi.weatherapp.ui.helper.getDateFromNow
import com.musashi.weatherapp.ui.helper.returnWeatherCode
import com.musashi.weatherapp.ui.screen.detailed.components.CityDetails
import com.musashi.weatherapp.ui.screen.detailed.components.NextDayWeatherSelector
import com.musashi.weatherapp.ui.screen.detailed.components.WeatherDetailsItemList
import com.musashi.weatherapp.ui.screen.detailed.components.WeatherDetailsTitle
import com.musashi.weatherapp.ui.screen.summary.WeatherState
import com.musashi.weatherapp.utils.Constants.TIME_EN
import com.musashi.weatherapp.utils.Constants.TIME_FA

@Composable
fun DetailedScreen(
    modifier: Modifier = Modifier,
    state: WeatherState,
    onBookmarkClick: () -> Unit,
    isCitySetAsDefault: () -> Boolean,
    isErrorOccurred: Boolean
) {

    var isExpanded1 by rememberSaveable { mutableStateOf(false) }
    var isExpanded2 by rememberSaveable { mutableStateOf(false) }

    var tabIndex by rememberSaveable { mutableStateOf(0) }

    var selectedDay by rememberSaveable { mutableStateOf(0) }

    val tabs = listOf(stringResource(R.string.today), stringResource(R.string.tomorrow), stringResource(R.string.the_day_after_tomorrow))

    if(state.currentCity.cityName != "") {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {

            CityDetails(
                weatherCodeImage = returnWeatherCode(state.weatherCurrentStatus?.current?.weatherCode ?: 0, state.weatherCurrentStatus?.current?.isDay ?: 1).imageId,
                weatherCodeTitle = returnWeatherCode(state.weatherCurrentStatus?.current?.weatherCode ?: 0, state.weatherCurrentStatus?.current?.isDay ?: 1).stringId,
                cityTitle = if(LocalLayoutDirection.current == LayoutDirection.Ltr) { state.currentCity.cityName } else{ state.currentCity.cityNameFa }?: state.currentCity.cityName,
                lat = state.currentCity.latitude,
                lng = state.currentCity.longitude,
                onFavoriteClick = {
                    onBookmarkClick()
                },
                isCitySetAsDefault = state.isDefaultCitySet && isCitySetAsDefault(),
                modifier = Modifier.verticalScroll(rememberScrollState())
            )
            TabRow(
                selectedTabIndex = tabIndex,
                modifier = Modifier,
            ) {
                tabs.forEachIndexed{ index, title ->
                    Tab(
                        text = { Text(text =  title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index}
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            if(!isErrorOccurred) {
                val currentTime = state.weatherCurrentStatus?.current?.time?.split(":")?.get(0) + ":00"
                val currentIndex = state.weatherFullStatus?.hourly?.time?.indexOf(currentTime) ?: 0
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(start = 5.dp, end = 5.dp)
                    ,
                ) {
                    when(tabIndex){
                        0 -> WeatherDetailsItemList(
                                state = state,
                                dayConditionStart = 0,
                                dayConditionEnd = 23,
                                currentIndex = currentIndex)
                        1 -> WeatherDetailsItemList(
                                state = state,
                                dayConditionStart = 24,
                                dayConditionEnd = 47,)
                        2 -> WeatherDetailsItemList(
                            state = state,
                            dayConditionStart = 48,
                            dayConditionEnd = 71,)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    WeatherDetailsTitle(
                        time = stringResource(R.string.next_days),
                        isExpanded = isExpanded1,
                        onTitleClick = {
                            isExpanded1 = !isExpanded1
                        }
                    )
                    AnimatedVisibility(visible = isExpanded1) {
                        if(state.forecastDays != 3) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                ScrollableTabRow(
                                    selectedTabIndex = selectedDay,
                                    edgePadding = 0.dp
                                ) {
                                    for (i in 3 until state.forecastDays) {
                                        Tab(
                                            text = { Text(text = getDateFromNow(i)) },
                                            selected = selectedDay == i - 3,
                                            onClick = { selectedDay = i - 3 }
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(30.dp))
                                NextDayWeatherSelector(state = state, selectedDay = selectedDay)
                            }
                        }else{
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(start = 40.dp, end = 40.dp)
                            ) {
                                Text(text = stringResource(R.string.next_days_forecast_text), textAlign = TextAlign.Center)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    WeatherDetailsTitle(
                        time = stringResource(R.string.charts),
                        isExpanded = isExpanded2,
                        onTitleClick = {
                            isExpanded2 = !isExpanded2
                        }
                    )
                    AnimatedVisibility(visible = isExpanded2) {
                        Spacer(modifier = Modifier.height(5.dp))
                        state.weatherFullStatus?.hourly?.let { hourlyStatus ->
                            WeatherLineChart(
                                xValues = if(LocalLayoutDirection.current == LayoutDirection.Ltr) TIME_EN else TIME_FA,
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
                    messageText = state.errorHourly.toString(),
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






