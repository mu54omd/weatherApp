package com.musashi.weatherapp.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.musashi.weatherapp.ui.screen.navgraph.NavGraph
import com.musashi.weatherapp.ui.screen.navgraph.Route
import com.musashi.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            val mainViewModel : MainViewModel = hiltViewModel()
            val state = mainViewModel.state.collectAsState().value

            splashScreen.apply {
                setKeepOnScreenCondition(
                    condition = {
                        !state.isThemeLoaded && !state.isLanguageLoaded
                    }
                )
            }
            WeatherAppTheme(
                appTheme = state.themeState
            ) {
                NavGraph(startDestination = Route.SummaryScreen.route)
            }

        }
    }
}