package com.musashi.weatherapp.ui.screen.navgraph

import androidx.compose.foundation.layout.padding
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
import com.musashi.weatherapp.R
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
    val state = summaryViewModel.state.collectAsState()
    val navController = rememberNavController()

    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.clear_sky, text = "Home"),
            BottomNavigationItem(icon = R.drawable.mainly_clear, text = "Details"),
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
                    state = state.value,
                    changeCity = { summaryViewModel.changeCity(it) },
                    nextHourWeather = summaryViewModel.getNextHourWeather(),
                    nextHourWeatherCode = summaryViewModel.getNextHourWeatherCode()
                )
            }

            composable(route = Route.DetailedScreen.route) {
                DetailedScreen(state = state.value)
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