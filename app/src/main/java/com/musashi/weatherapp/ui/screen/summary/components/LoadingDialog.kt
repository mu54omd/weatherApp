package com.musashi.weatherapp.ui.screen.summary.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun LoadingDialog(modifier: Modifier = Modifier) {

        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(
                dismissOnClickOutside = false
            )
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }

        }
}
@Preview
@Composable
private fun LoadingDialogPreview() {
    WeatherAppTheme {
        LoadingDialog()
    }
}