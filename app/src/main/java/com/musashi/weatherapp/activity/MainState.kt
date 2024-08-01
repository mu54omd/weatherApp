package com.musashi.weatherapp.activity

data class MainState(
    val themeState: AppTheme =  AppTheme.Light,
    val appLanguage: String = "en_US",
    val isThemeLoaded: Boolean = false,
    val isLanguageLoaded: Boolean = false,
)

enum class AppTheme {
    Dark,
    Light,
    Yellow,
    Red,
    Blue
}
