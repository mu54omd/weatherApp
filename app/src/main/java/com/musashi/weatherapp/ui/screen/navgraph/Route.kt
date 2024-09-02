package com.musashi.weatherapp.ui.screen.navgraph

sealed class Route(val route: String) {
    data object SummaryScreen: Route(route = "summaryScreen")
    data object DetailedScreen: Route(route = "detailedScreen")
    data object BookmarkScreen: Route(route = "bookmarkScreen")
    data object SettingsScreen: Route(route = "settingsScreen")
    data object AboutScreen: Route(route = "aboutScreen")
}