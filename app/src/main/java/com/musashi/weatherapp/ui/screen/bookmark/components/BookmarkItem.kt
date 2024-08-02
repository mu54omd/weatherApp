package com.musashi.weatherapp.ui.screen.bookmark.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.screen.common.shimmerEffect
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun BookmarkItem(
    modifier: Modifier = Modifier,
    onBookmarkCardClick: () -> Unit,
    onDeleteClick: () -> Unit,
    @DrawableRes weatherImageId: Int,
    @StringRes weatherTextId: Int,
    temperature: Double,
    cityName: String,
    countryName: String,
    isWeatherLoaded: Boolean
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(bottom = 10.dp)
        ,
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            onClick = onBookmarkCardClick,
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Box(
                modifier = Modifier.background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                            MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                        )
                    )
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 5.dp, end = 20.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                    ) {
                        if(isWeatherLoaded) {
                            Icon(
                                painter = painterResource(id = weatherImageId),
                                contentDescription = stringResource(id = weatherTextId),
                                modifier = Modifier.size(80.dp)
                            )
                            if(LocalLayoutDirection.current == LayoutDirection.Ltr) {
                                Text(
                                    text = "${temperature.toString()}°C",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                                )
                            }else{
                                Text(
                                    text = "${temperature.toString()}°C",
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                        }else{
                            Box(modifier = Modifier
                                .size(80.dp)
                                .clip(shape = MaterialTheme.shapes.extraLarge)
                                .shimmerEffect())
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 10.dp)
                            .weight(1f)
                    ) {
                        Text(text = cityName, style = MaterialTheme.typography.titleMedium)
                        Text(text = countryName)
                    }
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = stringResource(R.string.delete_bookmark),
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                onDeleteClick()
                            }
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview
@Composable
private fun BookmarkItemPreview() {
    WeatherAppTheme() {
        BookmarkItem(
            onBookmarkCardClick = {},
            onDeleteClick = {},
            weatherImageId = R.drawable.snow_fall,
            weatherTextId = R.string.snow_fall,
            temperature = 13.4,
            cityName = "City",
            countryName = "Country",
            isWeatherLoaded = true
        )
    }
}