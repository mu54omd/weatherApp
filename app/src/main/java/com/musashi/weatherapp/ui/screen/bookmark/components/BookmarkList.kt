package com.musashi.weatherapp.ui.screen.bookmark.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
import com.musashi.weatherapp.domain.model.BookmarkModel
import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.ui.common.EmptyScreen
import com.musashi.weatherapp.ui.helper.returnWeatherCode

@Composable
fun BookmarkList(
    modifier: Modifier = Modifier,
    bookmarkedCity: List<CityModel>,
    bookmarksResult: List<BookmarkModel>,
    onBookmarkCardClick: (CityModel) -> Unit,
    onDeleteClick: (BookmarkModel) -> Unit,
) {
    val bookmarksResultShadow = bookmarksResult
    val deletedItem = remember { mutableStateListOf<BookmarkModel>() }
    if(bookmarkedCity.isEmpty()){
        EmptyScreen(
            messageText = R.string.nothing_is_here,
            messageImage = Icons.Default.CollectionsBookmark
            )
    }else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(all = 10.dp)
        ) {
            items(
                items = bookmarksResultShadow,
                key = {
                    item -> item.cityModel.id
                }
            ) { item ->
                AnimatedVisibility(
                    visible = !deletedItem.contains(item),
                    exit = if(LocalLayoutDirection.current == LayoutDirection.Ltr){
                        slideOutHorizontally(
                            targetOffsetX = { -it },
                            animationSpec = tween(durationMillis = 300)
                        ) + shrinkVertically(
                            animationSpec = tween(delayMillis = 300)
                        )
                    }else{
                        slideOutHorizontally(
                            targetOffsetX = { it },
                            animationSpec = tween(durationMillis = 300)
                        ) + shrinkVertically(
                            animationSpec = tween(delayMillis = 300)
                        )
                    },
                    enter = fadeIn(
                        animationSpec = tween(durationMillis = 300)
                    )
                ) {
                        BookmarkItem(
                            onBookmarkCardClick = { onBookmarkCardClick(item.cityModel) },
                            weatherImageId = returnWeatherCode(
                                item.weatherCode,
                                isDay = item.isDay
                            ).imageId,
                            weatherTextId = returnWeatherCode(
                                item.weatherCode,
                                isDay = item.isDay
                            ).stringId,
                            temperature = item.temp,
                            cityName = if (LocalLayoutDirection.current == LayoutDirection.Ltr) item.cityModel.cityName else item.cityModel.cityNameFa
                                ?: item.cityModel.cityName,
                            countryName = if (LocalLayoutDirection.current == LayoutDirection.Ltr) item.cityModel.countryName else item.cityModel.countryNameFa,
                            onDeleteClick = {
                                deletedItem.add(item)
                                //onDeleteClick(item)
                            },
                            isWeatherLoaded = item.error == null
                        )
                }
            }
        }
        DisposableEffect(key1 = Unit) {
            onDispose {
                deletedItem.forEach {
                    onDeleteClick(it)
                }
            }
        }
    }
}