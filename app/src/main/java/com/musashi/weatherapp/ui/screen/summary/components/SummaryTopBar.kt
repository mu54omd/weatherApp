package com.musashi.weatherapp.ui.screen.summary.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.activity.AppTheme
import com.musashi.weatherapp.ui.common.LanguagePicker
import com.musashi.weatherapp.ui.common.LeftToRightLayout
import com.musashi.weatherapp.ui.common.ThemeSwitcher

@Composable
fun SummaryTopBar(
    isLanguageChecked : Boolean,
    onChangeLanguage: (String) -> Unit,
    onChangeTheme: (AppTheme) -> Unit

) {
    LeftToRightLayout {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 20.dp, start = 40.dp, end = 40.dp),

            ) {
            LanguagePicker(
                isChecked = isLanguageChecked,
                onCheckedClick = {
                    if(isLanguageChecked){
                        onChangeLanguage("en-US")
                    }else{
                        onChangeLanguage("fa")
                    }
                },
                modifier = Modifier.align(Alignment.TopStart)
            )
            ThemeSwitcher(
                changeTheme = { onChangeTheme(it)},
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}