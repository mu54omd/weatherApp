package com.musashi.weatherapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.musashi.weatherapp.ui.screen.navgraph.NavGraph
import com.musashi.weatherapp.ui.screen.navgraph.Route
import com.musashi.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val mainViewModel : MainViewModel = hiltViewModel()
            val state = mainViewModel.state.collectAsState().value

            WeatherAppTheme(
                appTheme = state.themeState
            ) {
                NavGraph(startDestination = Route.SummaryScreen.route)
            }
        }
    }
}