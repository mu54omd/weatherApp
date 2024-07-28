package com.musashi.weatherapp.ui.screen.bookmark.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    if(bookmarkedCity.isEmpty()){
        EmptyScreen(messageText = "Nothing is Here!")
    }else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(all = 10.dp)

        ) {
            items(bookmarksResult.size) {  i->
                val city = bookmarksResult[i]
                BookmarkItem(
                    onBookmarkCardClick = { onBookmarkCardClick(city.cityModel) },
                    weatherImageId = returnWeatherCode(city.weatherCode).imageId,
                    weatherTextId = returnWeatherCode(city.weatherCode).stringId,
                    temperature = city.temp,
                    cityName = city.cityModel.cityName,
                    countryName = city.cityModel.countryName,
                    onDeleteClick = { onDeleteClick(city.cityModel) }
                )
            }
        }
    }
}