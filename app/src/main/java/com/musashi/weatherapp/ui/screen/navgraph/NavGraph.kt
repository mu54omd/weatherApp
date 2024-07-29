package com.musashi.weatherapp.ui.screen.navgraph

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.musashi.weatherapp.ui.helper.getNextHourWeather
import com.musashi.weatherapp.ui.helper.getNextHourWeatherCode
import com.musashi.weatherapp.ui.helper.isCitySetAsDefault
import com.musashi.weatherapp.ui.screen.bookmark.BookmarkScreen
import com.musashi.weatherapp.ui.screen.detailed.DetailedScreen
import com.musashi.weatherapp.ui.screen.navgraph.components.BottomNavigationItem
import com.musashi.weatherapp.ui.screen.navgraph.components.WeatherBottomBar
import com.musashi.weatherapp.ui.screen.summary.SummaryScreen
import com.musashi.weatherapp.ui.screen.summary.SummaryViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val summaryViewModel: SummaryViewModel = hiltViewModel()
    val summaryState = summaryViewModel.state.collectAsState()
    val navController = rememberNavController()

    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = Icons.Outlined.Home, text = "Home"),
            BottomNavigationItem(icon = Icons.Outlined.Search, text = "Details"),
            BottomNavigationItem(icon = Icons.Outlined.FavoriteBorder, text = "Favorites"),
        )
    }
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = remember(key1 = backstackState){
        when(backstackState?.destination?.route){
            Route.SummaryScreen.route -> 0
            Route.DetailedScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    Scaffold(
        bottomBar = {
            WeatherBottomBar(
                items = bottomNavigationItem,
                selected = selectedItem,
                onItemClick = { index ->
                    when(index){
                        0 -> navigateToTab(navController = navController, route = Route.SummaryScreen.route)
                        1 -> navigateToTab(navController = navController, route = Route.DetailedScreen.route)
                        2 -> {
                            navigateToTab(navController = navController, route = Route.BookmarkScreen.route)
                        }
                    }
                }
            )
        },
        topBar = {},
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())
        ) {

            composable(route = Route.SummaryScreen.route) {

                SummaryScreen(
                    state = summaryState.value,
                    countryValue = summaryState.value.localCityCountry.first,
                    cityValue = summaryState.value.localCityCountry.second,
                    selectCountry = {country -> summaryViewModel.selectCountry(country)},
                    changeCity = {cityName, countryName -> summaryViewModel.selectCity(cityName, countryName) },
                    nextHourWeather = getNextHourWeather(state = summaryState.value),
                    nextHourWeatherCode = getNextHourWeatherCode(state = summaryState.value),
                    onAddFavoriteClick = { summaryViewModel.addToBookmark() }
                )
            }

            composable(route = Route.DetailedScreen.route) {
                DetailedScreen(
                    state = summaryState.value,
                    onBookmarkClick = { summaryViewModel.onSetDefaultCityClick() },
                    isCitySetAsDefault = { isCitySetAsDefault(state = summaryState.value) }
                )
            }

            composable(route = Route.BookmarkScreen.route){
                BookmarkScreen(
                    state = summaryState.value,
                    onDeleteClick = { city -> summaryViewModel.deleteFromBookmark(city) },
                    onBookmarkCardClick = { city ->
                        summaryViewModel.setSelectedAsCurrentCity(city)
                        navigateToTab(navController = navController, route = Route.DetailedScreen.route)
                    }
                )
            }
        }
    }

}

private fun navigateToTab(navController: NavController, route: String){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}