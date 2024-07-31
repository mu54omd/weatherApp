package com.musashi.weatherapp.ui.screen.summary.components

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun LanguagePicker(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedClick: () -> Unit,

) {
    Switch(
        checked = isChecked,
        onCheckedChange = {onCheckedClick()},
        thumbContent = {
            when(isChecked){
                true -> Text(text = "Fa")
                else -> Text(text = "En")
            }
        },
        colors = SwitchDefaults.colors(),
        modifier = modifier
    )
}

@Preview
@Composable
private fun LanguagePickerPreview() {
    WeatherAppTheme {
        LanguagePicker(
            isChecked = false,
            onCheckedClick = {}
        )
    }
}