package com.musashi.weatherapp.ui.screen.common

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
fun EmptyScreen(
    modifier: Modifier = Modifier,
    messageText: String,
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
        Text(text = messageText)
    }
}

@Preview
@Composable
private fun EmptyDetailsPreview() {
    WeatherAppTheme {
        EmptyScreen(
            messageText = "No City Selected!"
        )
    }
}