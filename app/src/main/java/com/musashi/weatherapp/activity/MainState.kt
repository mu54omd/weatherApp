package com.musashi.weatherapp.activity

data class MainState(
    val themeState: AppTheme = AppTheme.Light
)

enum class AppTheme {
    Dark,
    Light,
    Yellow,
    Red,
    Blue
}
