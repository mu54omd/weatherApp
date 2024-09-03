package com.musashi.weatherapp.ui.screen.About

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.common.AboutSettingsTopBar

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        AboutSettingsTopBar(
            onBackClick = { onBackButtonClick() },
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(stringResource(R.string.made_by_rage_and_hatred))
        }
    }
}