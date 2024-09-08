package com.musashi.weatherapp.ui.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    @StringRes messageText: Int,
    messageImage: ImageVector
) {
    Column(
        modifier = modifier.fillMaxSize().alpha(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = messageImage,
            contentDescription = stringResource(messageText),
        )
        Text(text = stringResource(messageText))
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview
@Composable
private fun EmptyDetailsPreview() {
    WeatherAppTheme {
        Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            EmptyScreen(
                messageText = R.string.no_city_selected,
                messageImage = Icons.Default.WrongLocation
            )
        }
    }
}