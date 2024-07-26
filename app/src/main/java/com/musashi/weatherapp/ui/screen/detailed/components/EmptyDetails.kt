package com.musashi.weatherapp.ui.screen.detailed.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun EmptyDetails(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().alpha(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Empty Details",
        )
        Text(text = "No City Selected!")
    }
}

@Preview
@Composable
private fun EmptyDetailsPreview() {
    WeatherAppTheme {
        EmptyDetails()
    }
}