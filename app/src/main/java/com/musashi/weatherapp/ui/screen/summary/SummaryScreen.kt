package com.musashi.weatherapp.ui.screen.summary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
import com.musashi.weatherapp.activity.AppTheme
import com.musashi.weatherapp.ui.screen.common.LeftToRightLayout
import com.musashi.weatherapp.ui.screen.summary.components.LanguagePicker
import com.musashi.weatherapp.ui.screen.summary.components.LoadingDialog
import com.musashi.weatherapp.ui.screen.summary.components.SummaryTopBar
import com.musashi.weatherapp.ui.screen.summary.components.ThemeSwitcher
import com.musashi.weatherapp.ui.screen.summary.components.WeatherSearchBar
import com.musashi.weatherapp.ui.screen.summary.components.WeatherStat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    countryValue: String,
    cityValue: String,
    state: WeatherState,
    selectCountry: (String) -> Unit,
    changeCity: (String, String) -> Unit,
    onAddFavoriteClick: () -> Unit,
    nextHourWeather: Double?,
    nextHourWeatherCode: Int?,
    changeTheme: (AppTheme) -> Unit,
    changeLanguage: (String) -> Unit,
    language: String,
) {
    var textValueCountry by rememberSaveable { mutableStateOf(countryValue) }
    var textValueCity by rememberSaveable { mutableStateOf(cityValue) }
    val scope = rememberCoroutineScope()
    var expandedCountry by remember { mutableStateOf(false) }
    var expandedCity by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SummaryTopBar(
                isLanguageChecked = language == "fa",
                onChangeLanguage = { changeLanguage(it) },
                onChangeTheme = { changeTheme(it) }
            )
        }
    ) {
        if(!state.isDatabaseLoaded){
            LoadingDialog()
        }else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding(), start = 30.dp, end = 30.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                WeatherSearchBar(
                    label = stringResource(R.string.search_bar_country),
                    textValue = textValueCountry,
                    onValueChange = {
                        textValueCountry = it
                        scope.launch {
                            delay(500)
                            selectCountry(it)
                        }
                        expandedCountry = textValueCountry.isNotEmpty()
                        textValueCity = ""
                    },
                    isLoading = false,
                    expanded = expandedCountry,
                    expandedChange = { expandedCountry = false },
                    countries = if(LocalLayoutDirection.current == LayoutDirection.Ltr) state.countries else state.countriesFa,
                    onSuggestionSelect = { title ->
                        textValueCountry = title
                        expandedCountry = false
                        selectCountry(title)
                    },
                    isEnabled = true,
                    onClearClicked = {
                        textValueCountry = ""
                        textValueCity = ""
                    },
                    onAddFavoriteClick = {}
                )

                Spacer(modifier = Modifier.size(10.dp))

                WeatherSearchBar(
                    label = stringResource(id = R.string.search_bar_city),
                    textValue = textValueCity,
                    onValueChange = {
                        textValueCity = it
                        scope.launch {
                            delay(500)
                            changeCity(it, textValueCountry)
                        }
                        expandedCity = textValueCity.isNotEmpty()
                    },
                    isLoading = state.isWeatherLoading,
                    expanded = expandedCity,
                    expandedChange = { expandedCity = false },
                    cities = state.cities,
                    onSuggestionSelect = { title ->
                        textValueCity = title
                        expandedCity = false
                        changeCity(title, textValueCountry)
                    },
                    isEnabled = state.isCountrySelected && textValueCountry.isNotEmpty(),
                    onClearClicked = { textValueCity = ""},
                    onAddFavoriteClick = onAddFavoriteClick
                )
                Spacer(modifier = Modifier.size(10.dp))
                WeatherStat(
                    cityName = if(LocalLayoutDirection.current == LayoutDirection.Ltr) {
                        state.currentCity.cityName.replaceFirstChar { char -> char.uppercaseChar() }
                    } else {
                        state.currentCity.cityNameFa
                    }?: state.currentCity.cityName.replaceFirstChar { char -> char.uppercaseChar() },

                    temperature = state.weatherStatus?.current?.temperature2m ?: 0.0,
                    humidity = state.weatherStatus?.current?.relativeHumidity2m ?: 0,
                    nextHourTemp = nextHourWeather ?: 0.0,
                    currentWeatherCode = state.weatherStatus?.current?.weatherCode ?: 0,
                    nextHourWeatherCode = nextHourWeatherCode ?: 0,
                    modifier = Modifier.fillMaxWidth(),
                    isWeatherLoaded = state.error == null
                )

            }
        }

    }

}

@Preview
@Composable
fun ModifiedPreview(modifier: Modifier = Modifier) {
    var isSelected = false
    LeftToRightLayout {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 20.dp, bottom = 20.dp, start = 40.dp, end = 40.dp),

            ) {
            LanguagePicker(
                isChecked = isSelected,
                onCheckedClick = {
                    isSelected = !isSelected
                },
                modifier = Modifier.align(Alignment.CenterStart)
            )
            ThemeSwitcher(
                changeTheme = { },
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}