package com.musashi.weatherapp.ui.screen.bookmark.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
import com.musashi.weatherapp.domain.model.BookmarkModel
import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.ui.helper.returnWeatherCode
import com.musashi.weatherapp.ui.screen.common.EmptyScreen

@Composable
fun BookmarkList(
    modifier: Modifier = Modifier,
    bookmarkedCity: List<CityModel>,
    bookmarksResult: List<BookmarkModel>,
    onBookmarkCardClick: (CityModel) -> Unit,
    onDeleteClick: (CityModel) -> Unit,
) {
    val deletedItem = remember { mutableStateListOf<BookmarkModel>() }
    if(bookmarkedCity.isEmpty()){
        EmptyScreen(
            messageText = stringResource(R.string.nothing_is_here),
            messageImage = Icons.Default.CollectionsBookmark
            )
    }else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(all = 10.dp)
        ) {
            items(bookmarksResult.size) { i ->
                val city = bookmarksResult[i]
                AnimatedVisibility(
                    visible = !deletedItem.contains(city),
                    exit = slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(durationMillis = 300)
                    ) + shrinkVertically(
                        animationSpec = tween(delayMillis = 300)
                    ),
                    enter = fadeIn(
                        animationSpec = tween(durationMillis = 300)
                    )
                ) {
                        BookmarkItem(
                            onBookmarkCardClick = { onBookmarkCardClick(city.cityModel) },
                            weatherImageId = returnWeatherCode(city.weatherCode).imageId,
                            weatherTextId = returnWeatherCode(city.weatherCode).stringId,
                            temperature = city.temp,
                            cityName = city.cityModel.cityName,
                            countryName = city.cityModel.countryName,
                            onDeleteClick = { deletedItem.add(city) },
                            isWeatherLoaded = city.error == null
                        )
                }
            }
        }
        DisposableEffect(key1 = Unit) {
            onDispose {
                deletedItem.forEach {
                    onDeleteClick(it.cityModel)
                }
            }
        }
    }
}