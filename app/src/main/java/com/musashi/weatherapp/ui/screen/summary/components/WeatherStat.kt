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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.ui.helper.returnHumidityImage
import com.musashi.weatherapp.ui.helper.returnWeatherCode
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherStat(
    modifier: Modifier = Modifier,
    cityName: String,
    temperature: Double,
    humidity: Int,
    nextHourTemp: Double,
    currentWeatherCode: Int,
    nextHourWeatherCode: Int
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
            WeatherItem(
                cardColor = MaterialTheme.colorScheme.secondaryContainer,
                image = returnWeatherCode(currentWeatherCode).imageId,
                title = "Now",
                value = temperature.toString(), unit = "°C",
                offsetMillis = 0
            )
            WeatherItem(
                cardColor = MaterialTheme.colorScheme.tertiaryContainer,
                image = returnHumidityImage(humidity),
                title = "Humidity",
                value = humidity.toString(),
                unit = "%",
                offsetMillis = 200
            )
            WeatherItem(
                cardColor = MaterialTheme.colorScheme.primaryContainer,
                image = returnWeatherCode(nextHourWeatherCode).imageId,
                title = "Next Hour",
                value = nextHourTemp.toString(),
                unit = "°C",
                offsetMillis = 500
            )
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
            humidity = 74,
            nextHourTemp = 23.3,
            currentWeatherCode = 1,
            nextHourWeatherCode = 1
        )
    }

}