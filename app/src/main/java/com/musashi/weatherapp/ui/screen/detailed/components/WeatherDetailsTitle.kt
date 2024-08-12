package com.musashi.weatherapp.ui.screen.detailed.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherDetailsTitle(
    modifier: Modifier = Modifier,
    time: String,
    isExpanded: Boolean,
    onTitleClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)
            .clickable {
                onTitleClick()
            }
        ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceTint
        ),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize().padding(start = 10.dp, end = 10.dp)
        ) {
            Text(text = time)
            Icon(
                imageVector = if(!isExpanded) Icons.Default.ExpandMore else Icons.Default.ExpandLess,
                contentDescription = "expand status")
        }
    }
}

@Preview
@Composable
private fun WeatherDetailsPreview() {
    WeatherAppTheme {
        WeatherDetailsTitle(
            time = "Time",
            isExpanded = true,
            onTitleClick = {}
        )
    }
}