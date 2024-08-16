package com.musashi.weatherapp.activity

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musashi.weatherapp.domain.preferences.LocalUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val localUserManager: LocalUserManager
): ViewModel(){

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        loadThemeColorAndAppLanguage()
    }

    fun changeTheme(themeName: AppTheme){
        _state.update {
            when (themeName) {
                AppTheme.Dark -> it.copy(themeState =AppTheme.Dark)
                AppTheme.Red -> it.copy(themeState =AppTheme.Red)
                AppTheme.Blue -> it.copy(themeState =AppTheme.Blue)
                AppTheme.Yellow -> it.copy(themeState =AppTheme.Yellow)
                AppTheme.Light -> it.copy(themeState =AppTheme.Light)
            }
        }
        viewModelScope.launch {
            localUserManager.saveThemeState(themeName.name)
        }
    }

    fun changeLanguage(locale: String){
        _state.update { it.copy(appLanguage = locale) }
        viewModelScope.launch {
            localUserManager.saveAppLanguage(locale)
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(locale))
        }
    }

    private fun loadThemeColorAndAppLanguage(){
        viewModelScope.launch {
            _state.update { it.copy(
                themeState = AppTheme.valueOf(localUserManager.readThemeState().first()),
                appLanguage = localUserManager.readAppLanguage().first()
                )
            }
            delay(500)
            _state.update { it.copy(
                isThemeLoaded = true,
                isLanguageLoaded = true
            ) }
        }
    }
}