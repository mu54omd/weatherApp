package com.musashi.weatherapp.ui.screen.about

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.common.AboutSettingsTopBar

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit
) {
    BackHandler {
        onBackButtonClick()
    }
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        val(topBar, content) = createRefs()
        AboutSettingsTopBar(
            onBackClick = { onBackButtonClick() },
            modifier = Modifier.constrainAs(topBar){
                top.linkTo(parent.top, margin = 16.dp)
            }
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.constrainAs(content){
                top.linkTo(topBar.bottom, margin = 250.dp)
                start.linkTo(parent.start, margin = 50.dp)
                end.linkTo(parent.end, margin = 50.dp)
            }
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.app_name))
                Text("  -  " + stringResource(R.string.version))
                Text(stringResource(R.string.app_version))
            }
            Text(stringResource(R.string.made_by_rage_and_hatred))
            Row {
                Icon(imageVector = Icons.Default.Celebration, contentDescription = "Celebration")
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
                Icon(imageVector = Icons.Default.Dangerous, contentDescription = "Dangerous")
            }
        }
    }
}