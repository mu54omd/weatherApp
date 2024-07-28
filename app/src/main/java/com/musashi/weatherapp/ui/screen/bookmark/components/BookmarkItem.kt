package com.musashi.weatherapp.ui.screen.bookmark.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
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
    countryName: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            onClick = onBookmarkCardClick,
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            ),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 5.dp, end = 5.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = painterResource(id = weatherImageId),
                        contentDescription = stringResource(id = weatherTextId),
                        modifier = Modifier.size(80.dp)
                    )
                    Text(
                        text = "${temperature.toString()}°C",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .weight(1f)
                ) {
                    Text(text = cityName, style = MaterialTheme.typography.titleMedium)
                    Text(text = countryName)
                }
                Image(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Delete Bookmark",
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

@Preview
@Composable
private fun BookmarkItemPreview() {
    WeatherAppTheme {
        BookmarkItem(
            onBookmarkCardClick = {},
            onDeleteClick = {},
            weatherImageId = R.drawable.snow_fall,
            weatherTextId = R.string.snow_fall,
            temperature = 13.4,
            cityName = "City",
            countryName = "Country"
        )
    }
}