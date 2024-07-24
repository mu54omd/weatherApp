package com.musashi.weatherapp.ui.screen.detailed.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherDetailsItem(
    modifier: Modifier = Modifier,
    date: String,
    time: String,
    temp: Double,
    humidity: Int,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Green.copy(alpha = 0.3f)
        ),
        shape = MaterialTheme.shapes.extraSmall
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize().padding(start = 10.dp, end = 10.dp)
        ) {

            Text(
                text = date,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            )

            Text(
                text = time,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            )

            Text(
                text = "${temp.toString()}°C",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "${humidity.toString()}%",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview
@Composable
private fun WeatherDetailsPreview() {
    WeatherAppTheme {
        WeatherDetailsItem(date = "2024-07-24",time = "12:00", temp = 43.2, humidity = 23)
    }
}