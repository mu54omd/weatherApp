package com.musashi.weatherapp.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musashi.weatherapp.domain.preferences.LocalUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
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
        loadThemeColor()
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

    private fun loadThemeColor(){
        viewModelScope.launch {
            _state.update {
                it.copy(themeState = AppTheme.valueOf(localUserManager.readThemeState().first()))
            }
        }
    }
}