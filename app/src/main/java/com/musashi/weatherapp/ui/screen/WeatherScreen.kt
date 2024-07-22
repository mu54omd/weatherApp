package com.musashi.weatherapp.ui.screen

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
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.musashi.weatherapp.ui.screen.components.LoadingDialog
import com.musashi.weatherapp.ui.screen.components.SuggestionListItem
import com.musashi.weatherapp.ui.screen.components.WeatherStat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    var textValue by rememberSaveable { mutableStateOf("") }
    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(false) }


    if(state.value.isCityLoading){
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
                        viewModel.changeCity(it)
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
                    if(state.value.isWeatherLoading){
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
                                state.value.cities.filter {
                                it.cityName.lowercase().contains(textValue.lowercase())
                                }
                            ){
                                SuggestionListItem(title = it.cityName) { title ->
                                    textValue = title
                                    expanded = false
                                    viewModel.changeCity(title)
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            WeatherStat(
                cityName = state.value.currentCity.cityName.replaceFirstChar { char -> char.uppercaseChar() },
                temperature = state.value.weatherStatus?.current?.temperature2m ?: 0.0,
                humidity = state.value.weatherStatus?.current?.relativeHumidity2m ?: 0,
                nextHourTemp = viewModel.getNextHourWeather() ?: 0.0,
                currentWeatherCode = state.value.weatherStatus?.current?.weatherCode ?: 0,
                nextHourWeatherCode = viewModel.getNextHourWeatherCode() ?: 0,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}