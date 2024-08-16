package com.musashi.weatherapp.activity

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.musashi.weatherapp.ui.screen.navgraph.NavGraph
import com.musashi.weatherapp.ui.screen.navgraph.Route
import com.musashi.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mainViewModel : MainViewModel = hiltViewModel()
            val state = mainViewModel.state.collectAsState().value
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val postNotificationPermission =
                    rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

                LaunchedEffect(key1 = true) {
                    if (!postNotificationPermission.status.isGranted) {
                        postNotificationPermission.launchPermissionRequest()
                    }
                }
            }
            splashScreen.apply {
                setKeepOnScreenCondition(
                    condition = {
                        !state.isThemeLoaded || !state.isLanguageLoaded
                    }
                )
            }
            WeatherAppTheme(
                appTheme = state.themeState
            ) {
                NavGraph(
                    startDestination = Route.SummaryScreen.route,
                    mainViewModel = mainViewModel
                )
            }

        }
    }
}