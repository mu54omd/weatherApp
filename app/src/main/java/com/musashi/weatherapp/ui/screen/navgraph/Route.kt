package com.musashi.weatherapp.ui.screen.navgraph

sealed class Route(val route: String) {
    data object SummaryScreen: Route(route = "summaryScreen")
    data object DetailedScreen: Route(route = "detailedScreen")
}