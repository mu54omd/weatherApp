package com.musashi.weatherapp.ui.screen.summary.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.helper.returnHumidityImage
import com.musashi.weatherapp.ui.helper.returnWeatherCode
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherStat(
    modifier: Modifier = Modifier,
    cityName: String,
    temperature: Double,
    apparentTemperature: Double,
    humidity: Int,
    nextHourTemp: Double,
    currentWeatherCode: Int,
    nextHourWeatherCode: Int,
    isWeatherLoaded: Boolean,
) {

    Column(
        modifier = modifier. fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = cityName,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(

        ) {
            Column {

                WeatherItem(
                    cardColor = listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f),
                    ),
                    shimmerColor = MaterialTheme.colorScheme.secondaryContainer,
                    image = returnWeatherCode(currentWeatherCode).imageId,
                    title = stringResource(R.string.now_temp),
                    value = temperature.toString(), unit = "°C",
                    offsetMillis = 0,
                    isWeatherLoaded = isWeatherLoaded
                )
                WeatherItem(
                    cardColor = listOf(
                        MaterialTheme.colorScheme.tertiaryContainer,
                        MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f),
                    ),
                    shimmerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    image = returnHumidityImage(humidity),
                    title = stringResource(R.string.humidity),
                    value = humidity.toString(),
                    unit = "%",
                    offsetMillis = 200,
                    isWeatherLoaded = isWeatherLoaded
                )
            }
            Column {
                WeatherItem(
                    cardColor = listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f),
                    ),
                    shimmerColor = MaterialTheme.colorScheme.primaryContainer,
                    image = returnWeatherCode(nextHourWeatherCode).imageId,
                    title = stringResource(R.string.next_hour_temp),
                    value = nextHourTemp.toString(),
                    unit = "°C",
                    offsetMillis = 500,
                    isWeatherLoaded = isWeatherLoaded
                )
                WeatherItem(
                    cardColor = listOf(
                        MaterialTheme.colorScheme.tertiaryContainer,
                        MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f),
                    ),
                    shimmerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    image = returnWeatherCode(currentWeatherCode).imageId,
                    title = stringResource(R.string.apparent_temp),
                    value = apparentTemperature.toString(),
                    unit = "°C",
                    offsetMillis = 500,
                    isWeatherLoaded = isWeatherLoaded
                )
            }
        }
    }
}

@Preview
@Composable
private fun WeatherStatPreview() {
    WeatherAppTheme {
        WeatherStat(
            cityName = "Tehran",
            temperature = 22.4,
            apparentTemperature = 34.2,
            humidity = 74,
            nextHourTemp = 23.3,
            currentWeatherCode = 1,
            nextHourWeatherCode = 1,
            isWeatherLoaded = false
        )
    }

}