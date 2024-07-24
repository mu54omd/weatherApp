package com.musashi.weatherapp.ui.screen.summary

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.ui.screen.summary.components.LoadingDialog
import com.musashi.weatherapp.ui.screen.summary.components.SuggestionListItem
import com.musashi.weatherapp.ui.screen.summary.components.WeatherStat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    state: WeatherState,
    changeCity: (String) -> Unit,
    nextHourWeather: Double?,
    nextHourWeatherCode: Int?,
) {
    var textValue by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(false) }


    if(state.isCityLoading){
        LoadingDialog()
    }else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {
            TextField(
                value = textValue,
                label = { Text(text = "City")},
                onValueChange = {
                    textValue = it
                    scope.launch {
                        delay(500)
                        changeCity(it)
                    }
                    expanded = textValue.isNotEmpty()
                },
                modifier = Modifier.width(300.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Place,
                        contentDescription = "")
                },
                trailingIcon = {
                    if(state.isWeatherLoading){
                        CircularProgressIndicator( modifier = Modifier.size(25.dp))
                    }
                },
                shape = MaterialTheme.shapes.extraLarge,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            AnimatedVisibility(visible = expanded) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f)
                    )
                ) {
                    LazyColumn(modifier = Modifier.height(100.dp)) {
                        if(textValue.isNotEmpty()){
                            items(
                                state.cities.filter {
                                it.cityName.lowercase().contains(textValue.lowercase())
                                }
                            ){
                                SuggestionListItem(title = it.cityName) { title ->
                                    textValue = title
                                    expanded = false
                                    changeCity(title)
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            WeatherStat(
                cityName = state.currentCity.cityName.replaceFirstChar { char -> char.uppercaseChar() },
                temperature = state.weatherStatus?.current?.temperature2m ?: 0.0,
                humidity = state.weatherStatus?.current?.relativeHumidity2m ?: 0,
                nextHourTemp = nextHourWeather ?: 0.0,
                currentWeatherCode = state.weatherStatus?.current?.weatherCode ?: 0,
                nextHourWeatherCode = nextHourWeatherCode ?: 0,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}