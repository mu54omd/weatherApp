package com.musashi.weatherapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
            WeatherAppTheme {
                NavGraph(startDestination = Route.SummaryScreen.route)
            }
        }
    }
}