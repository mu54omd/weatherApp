package com.musashi.weatherapp.ui.screen.detailed.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherDetailsItemCard(
    modifier: Modifier = Modifier,
    time: String,
    @DrawableRes weatherIcon: Int,
    temperature: Double,
    humidity: Int
) {
    Card(
        modifier = modifier
            .width(100.dp)
            .height(120.dp)
            .padding(start = 5.dp, end = 5.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        )
        {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(color = MaterialTheme.colorScheme.onPrimary)
                    .fillMaxWidth()
                ,
            ) {
                Text(
                    text = time,
                    style =
                    if (LocalLayoutDirection.current == LayoutDirection.Ltr)
                        MaterialTheme.typography.bodyMedium
                    else
                        MaterialTheme.typography.bodySmall
                )
            }
            Image(
                painter = painterResource(id = weatherIcon),
                contentDescription = "",
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = "${temperature}°C",
                style =
                if(LocalLayoutDirection.current == LayoutDirection.Ltr)
                    MaterialTheme.typography.bodyMedium
                else
                    MaterialTheme.typography.bodySmall
                )
            Text(
                text = "$humidity%",
                style =
                if(LocalLayoutDirection.current == LayoutDirection.Ltr)
                    MaterialTheme.typography.bodyMedium
                else
                    MaterialTheme.typography.bodySmall
                )
        }
    }
}

@Preview
@Composable
private fun WeatherDetailsItemCardPreview() {
    WeatherAppTheme {
        WeatherDetailsItemCard(
            time = "13:00",
            weatherIcon = R.drawable.clear_sky,
            temperature = 33.4,
            humidity = 40
        )
    }

}