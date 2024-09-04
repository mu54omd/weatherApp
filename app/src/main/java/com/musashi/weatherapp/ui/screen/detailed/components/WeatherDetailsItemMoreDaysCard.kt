package com.musashi.weatherapp.ui.screen.detailed.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherDetailsItemMoreDaysCard(
    modifier: Modifier = Modifier,
    time: String,
    @DrawableRes weatherIcon: Int,
    temperature: Double,
) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .width(50.dp)
                .height(80.dp)
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(20))
        )
        {
            Text(
                text = time,
                style =
                if (LocalLayoutDirection.current == LayoutDirection.Ltr)
                    MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp)
                else
                    MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp)
            )

            Icon(
                painter = painterResource(id = weatherIcon),
                contentDescription = "",
                modifier = Modifier.size(30.dp),
            )
            Text(
                text = "${temperature}°C",
                style =
                if (LocalLayoutDirection.current == LayoutDirection.Ltr)
                    MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp)
                else
                    MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp)
            )
        }
}

@Preview
@Composable
private fun WeatherDetailsItemCardPreview() {
    WeatherAppTheme {
        WeatherDetailsItemMoreDaysCard(
            time = "13:00",
            weatherIcon = R.drawable.clear_sky,
            temperature = 33.4,
        )
    }

}