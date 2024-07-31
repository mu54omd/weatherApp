package com.musashi.weatherapp.ui.screen.detailed.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherDetailsTitle(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceTint
        ),
        shape = MaterialTheme.shapes.extraSmall
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.date),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.width(60.dp))
            Text(
                text = stringResource(R.string.time),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = stringResource(R.string.temperature),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = stringResource(id = R.string.humidity),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview
@Composable
private fun WeatherDetailsPreview() {
    WeatherAppTheme() {
        WeatherDetailsTitle()
    }
}