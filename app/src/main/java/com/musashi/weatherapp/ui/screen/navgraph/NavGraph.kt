package com.musashi.weatherapp.ui.screen.navgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Boy
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.musashi.weatherapp.R
import com.musashi.weatherapp.activity.MainViewModel
import com.musashi.weatherapp.ui.helper.getNextHourWeather
import com.musashi.weatherapp.ui.helper.getNextHourWeatherCode
import com.musashi.weatherapp.ui.helper.isCitySetAsDefault
import com.musashi.weatherapp.ui.screen.bookmark.BookmarkScreen
import com.musashi.weatherapp.ui.screen.detailed.DetailedScreen
import com.musashi.weatherapp.ui.screen.navgraph.components.AboutDialog
import com.musashi.weatherapp.ui.screen.navgraph.components.BottomNavigationItem
import com.musashi.weatherapp.ui.screen.navgraph.components.WeatherBottomBar
import com.musashi.weatherapp.ui.screen.summary.SummaryScreen
import com.musashi.weatherapp.ui.screen.summary.SummaryViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    startDestination: String,
    mainViewModel: MainViewModel
) {
    val summaryViewModel: SummaryViewModel = hiltViewModel()
    val summaryState = summaryViewModel.state.collectAsState()

    val pullRefreshState = rememberPullToRefreshState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerScope = rememberCoroutineScope()

    val navController = rememberNavController()

    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = Icons.Outlined.Home, text = R.string.home),
            BottomNavigationItem(icon = Icons.Outlined.Search, text = R.string.details),
            BottomNavigationItem(icon = Icons.Outlined.FavoriteBorder, text = R.string.favorites),
        )
    }
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = remember(key1 = backstackState) {
        when (backstackState?.destination?.route) {
            Route.SummaryScreen.route -> 0
            Route.DetailedScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.9f)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Logo",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.size(250.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.width(250.dp),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(id = R.string.settings)) },
                    icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = stringResource(R.string.settings))},
                    onClick = { drawerScope.launch { drawerState.close() }},
                    selected = false,
                    modifier = Modifier.width(250.dp)
                )
                NavigationDrawerItem(
                    label = { Text(stringResource(id = R.string.about)) },
                    icon = { Icon(imageVector = Icons.Default.Boy, contentDescription = stringResource(R.string.about))},
                    onClick = {
                        drawerScope.launch { drawerState.close() }
                        navigateToTab(navController = navController, route = Route.AboutScreen.route)
                        },
                    selected = false,
                    modifier = Modifier.width(250.dp)

                )
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                WeatherBottomBar(
                    items = bottomNavigationItem,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.SummaryScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.DetailedScreen.route
                            )

                            2 -> {
                                navigateToTab(
                                    navController = navController,
                                    route = Route.BookmarkScreen.route
                                )
                            }
                        }
                    }
                )
            },
            topBar = {},
        ) {
            PullToRefreshBox(
                isRefreshing = summaryState.value.isRefreshing,
                state = pullRefreshState,
                onRefresh = summaryViewModel::refresh,
                modifier = Modifier.fillMaxSize(),
                indicator = {
                    PullToRefreshDefaults.Indicator(
                        state = pullRefreshState,
                        isRefreshing = summaryState.value.isRefreshing,
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = startDestination,
                    modifier = Modifier
                        .padding(
                            bottom = it.calculateBottomPadding(),
                            top = it.calculateTopPadding()
                        )
                ) {
                    composable(route = Route.SummaryScreen.route) {

                        SummaryScreen(
                            state = summaryState.value,
                            countryValue = summaryState.value.localCityCountry.first,
                            cityValue = summaryState.value.localCityCountry.second,
                            selectCountry = { country -> summaryViewModel.selectCountry(country) },
                            changeCity = { cityName, countryName ->
                                summaryViewModel.selectCity(
                                    cityName,
                                    countryName
                                )
                            },
                            nextHourWeather = getNextHourWeather(state = summaryState.value),
                            nextHourWeatherCode = getNextHourWeatherCode(state = summaryState.value),
                            onAddFavoriteClick = { summaryViewModel.addToBookmark() },
                            changeTheme = { theme -> mainViewModel.changeTheme(theme) },
                            changeLanguage = { locale -> mainViewModel.changeLanguage(locale) },
                            language = mainViewModel.state.value.appLanguage
                        )
                    }

                    composable(route = Route.DetailedScreen.route) {
                        DetailedScreen(
                            state = summaryState.value,
                            onBookmarkClick = { summaryViewModel.onSetDefaultCityClick() },
                            isCitySetAsDefault = { isCitySetAsDefault(state = summaryState.value) },
                            isErrorOccurred = summaryState.value.errorHourly != null,
                        )
                    }

                    composable(route = Route.BookmarkScreen.route) {
                        BookmarkScreen(
                            state = summaryState.value,
                            onDeleteClick = { city -> summaryViewModel.deleteFromBookmark(city) },
                            onBookmarkCardClick = { city ->
                                summaryViewModel.setSelectedAsCurrentCity(city)
                                navigateToTab(
                                    navController = navController,
                                    route = Route.DetailedScreen.route
                                )
                            }
                        )
                    }

                    composable(route = Route.SettingsScreen.route){

                    }
                    composable(route = Route.AboutScreen.route){
                        AboutDialog()
                    }
                }
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