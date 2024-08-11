package com.musashi.weatherapp.ui.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WrongLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    messageText: String,
    messageImage: ImageVector
) {
    Column(
        modifier = modifier.fillMaxSize().alpha(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = messageImage,
            contentDescription = messageText,
        )
        Text(text = messageText)
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview
@Composable
private fun EmptyDetailsPreview() {
    WeatherAppTheme() {
        Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            EmptyScreen(
                messageText = "No City Selected!",
                messageImage = Icons.Default.WrongLocation
            )
        }
    }
}