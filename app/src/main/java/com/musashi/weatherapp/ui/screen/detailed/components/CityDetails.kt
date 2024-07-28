package com.musashi.weatherapp.ui.screen.detailed.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun CityDetails(
    modifier: Modifier = Modifier,
    isCitySetAsDefault: Boolean,
    onFavoriteClick: () -> Unit,
    @DrawableRes weatherCodeImage: Int,
    @StringRes weatherCodeTitle: Int,
    cityTitle: String,
    lat: Double,
    lng: Double
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 20.dp, end = 20.dp),
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = weatherCodeImage),
                    contentDescription = stringResource(id = weatherCodeTitle),
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.secondaryContainer)
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Location"
                    )
                    Text(
                        text = cityTitle,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(space = 50.dp)
                ) {
                    Text(
                        text = lat.toString(),
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = lng.toString(),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(alignment = BiasAlignment(-0.9f, -0.9f))
        ) {
            Image(
                imageVector = if (isCitySetAsDefault) Icons.Filled.BookmarkAdded else Icons.Rounded.BookmarkBorder,
                contentDescription = "Set as Default",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .clickable { onFavoriteClick() }
                    .padding(4.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = if(isCitySetAsDefault) "Default" else "Set as Default",
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview
@Composable
private fun CityDetailsPreview() {
    WeatherAppTheme {
        CityDetails(
            isCitySetAsDefault = true,
            onFavoriteClick = {},
            weatherCodeImage = R.drawable.clear_sky,
            weatherCodeTitle = R.string.clear_sky,
            cityTitle = "Tonekabon",
            lat = 36.8163,
            lng = 50.8738

        )
    }
}