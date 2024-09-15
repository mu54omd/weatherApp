package com.musashi.weatherapp.ui.screen.bookmark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.musashi.weatherapp.domain.model.BookmarkModel
import com.musashi.weatherapp.domain.model.CityModel
import com.musashi.weatherapp.ui.screen.bookmark.components.BookmarkList
import com.musashi.weatherapp.ui.screen.summary.WeatherState

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    state: WeatherState,
    onBookmarkCardClick: (CityModel) -> Unit,
    onDeleteClick: (BookmarkModel) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ){
        BookmarkList(
            bookmarkedCity = state.bookmarkedCities,
            bookmarksResult = state.result,
            onBookmarkCardClick = { onBookmarkCardClick(it) },
            onDeleteClick = { onDeleteClick(it) }
        )
    }

}