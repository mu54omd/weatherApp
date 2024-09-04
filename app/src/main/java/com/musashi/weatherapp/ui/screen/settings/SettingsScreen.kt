package com.musashi.weatherapp.ui.screen.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.musashi.weatherapp.R
import com.musashi.weatherapp.activity.AppTheme
import com.musashi.weatherapp.ui.common.AboutSettingsTopBar
import com.musashi.weatherapp.ui.common.LanguagePicker
import com.musashi.weatherapp.ui.common.ThemeSwitcher
import com.musashi.weatherapp.ui.screen.settings.components.SettingItem
import com.musashi.weatherapp.ui.theme.WeatherAppTheme
import com.musashi.weatherapp.utils.Constants.forecastMenuEn
import com.musashi.weatherapp.utils.Constants.forecastMenuFa

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit,
    onThemeColorClick: (AppTheme) -> Unit,
    onLanguageClick: (String) -> Unit,
    onForecastDaysClick: (Int) -> Unit,
    language: String,
    forecastDays: Int
) {
    BackHandler {
        onBackButtonClick()
    }
    ConstraintLayout(
        modifier = modifier.fillMaxSize(),
    ) {
        val(topBar, content) = createRefs()
        AboutSettingsTopBar(
            onBackClick = { onBackButtonClick() },
            modifier = Modifier.constrainAs(topBar){
                top.linkTo(parent.top, margin = 16.dp)
            }
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.constrainAs(content){
                top.linkTo(topBar.bottom, margin = 150.dp)
                start.linkTo(parent.start, margin = 50.dp)
                end.linkTo(parent.end, margin = 50.dp)
            }
        ) {
            Text(text = stringResource(R.string.settings), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(modifier = Modifier.width(300.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, bottom = 20.dp),
                ) {
                Text(text = stringResource(R.string.language))
                LanguagePicker(
                    isChecked = language == "fa",
                    onCheckedClick = {
                        if(language == "fa"){
                            onLanguageClick("en-US")
                        }else{
                            onLanguageClick("fa")
                        }
                    }
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, bottom = 20.dp),
            ) {
                Text(text = stringResource(R.string.theme_color))
                ThemeSwitcher(
                    changeTheme = onThemeColorClick
                )
            }

            SettingItem(
                title = stringResource(R.string.forecast_days),
                defaultIndex = forecastMenuEn.indexOf(forecastDays.toString()),
                menuContent = if(LocalLayoutDirection.current == LayoutDirection.Ltr) forecastMenuEn else forecastMenuFa,
                onMenuItemClick = { onForecastDaysClick(it.toInt()) }
            )
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    WeatherAppTheme {
        SettingsScreen(
            onBackButtonClick = {},
            onLanguageClick = {},
            onThemeColorClick = {},
            onForecastDaysClick = {},
            forecastDays = 14,
            language = "fa"
        )
    }

}