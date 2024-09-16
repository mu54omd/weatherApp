package com.musashi.weatherapp.ui.screen.summary

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.constraintlayout.compose.ConstraintLayout
import com.musashi.weatherapp.R
import com.musashi.weatherapp.activity.AppTheme
import com.musashi.weatherapp.ui.common.LanguagePicker
import com.musashi.weatherapp.ui.common.LeftToRightLayout
import com.musashi.weatherapp.ui.common.ThemeSwitcher
import com.musashi.weatherapp.ui.screen.WeatherState
import com.musashi.weatherapp.ui.screen.summary.components.LoadingDialog
import com.musashi.weatherapp.ui.screen.summary.components.SummaryTopBar
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
    isNextHourDay: Int?,
    changeTheme: (AppTheme) -> Unit,
    changeLanguage: (String) -> Unit,
    language: String,
) {
    var textValueCountry by rememberSaveable { mutableStateOf(countryValue) }
    var textValueCity by rememberSaveable { mutableStateOf(cityValue) }
    val scope = rememberCoroutineScope()
    var expandedCountry by remember { mutableStateOf(false) }
    var expandedCity by remember { mutableStateOf(false) }


    if(!state.isDatabaseLoaded){
        LoadingDialog()
    }else {
        ConstraintLayout(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {
            val (topBar, searchBar1, searchBar2, status) = createRefs()
            SummaryTopBar(
                isLanguageChecked = language == "fa",
                onChangeLanguage = { changeLanguage(it) },
                onChangeTheme = { changeTheme(it) },
                modifier = Modifier
                    .constrainAs(topBar){
                        top.linkTo(parent.top, margin = 16.dp)
                    }
            )

            WeatherSearchBar(
                label = stringResource(R.string.search_bar_country),
                textValue = textValueCountry,
                onValueChange = { country ->
                    textValueCountry = country
                    scope.launch {
                        delay(500)
                        selectCountry(country)
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
                    expandedCountry = false
                    expandedCity = false
                },
                onAddFavoriteClick = {},
                modifier = Modifier
                    .constrainAs(searchBar1){
                        top.linkTo(topBar.bottom, margin = 50.dp)
                        centerHorizontallyTo(parent)
                    }
            )

            WeatherSearchBar(
                label = stringResource(id = R.string.search_bar_city),
                textValue = textValueCity,
                onValueChange = { city ->
                    textValueCity = city
                    scope.launch {
                        delay(500)
                        changeCity(city, textValueCountry)
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
                onClearClicked = {
                    textValueCity = ""
                    expandedCity = false
                                 },
                onAddFavoriteClick = onAddFavoriteClick,
                modifier = Modifier
                    .constrainAs(searchBar2){
                        top.linkTo(searchBar1.bottom, margin = 5.dp)
                        centerHorizontallyTo(parent)
                    }
            )
            WeatherStat(
                cityName = if(LocalLayoutDirection.current == LayoutDirection.Ltr) {
                    state.currentCity.cityName.replaceFirstChar { char -> char.uppercaseChar() }
                } else {
                    state.currentCity.cityNameFa
                }?: state.currentCity.cityName.replaceFirstChar { char -> char.uppercaseChar() },

                temperature = state.weatherCurrentStatus?.current?.temperature2m ?: 0.0,
                apparentTemperature = state.weatherCurrentStatus?.current?.apparentTemperature ?: 0.0,
                humidity = state.weatherCurrentStatus?.current?.relativeHumidity2m ?: 0,
                nextHourTemp = nextHourWeather ?: 0.0,
                currentWeatherCode = state.weatherCurrentStatus?.current?.weatherCode ?: 0,
                isNowDay = state.weatherCurrentStatus?.current?.isDay?: 1,
                nextHourWeatherCode = nextHourWeatherCode ?: 0,
                isNextHourDay = isNextHourDay?: 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(status){
                        top.linkTo(searchBar2.bottom, margin = 10.dp)
                        centerHorizontallyTo(parent)
                    },
                isWeatherLoaded = state.errorCurrent == null
            )
        }
    }



}

@Preview
@Composable
fun ModifiedPreview(modifier: Modifier = Modifier) {
    val isSelected = remember { mutableStateOf(false) }
    LeftToRightLayout {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 20.dp, bottom = 20.dp, start = 40.dp, end = 40.dp),

            ) {
            LanguagePicker(
                isChecked = isSelected.value,
                onCheckedClick = {
                    isSelected.value = !isSelected.value
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