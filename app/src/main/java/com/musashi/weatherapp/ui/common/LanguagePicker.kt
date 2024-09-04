package com.musashi.weatherapp.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun LanguagePicker(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedClick: () -> Unit,
) {
    Switch(
        checked = isChecked,
        onCheckedChange = { onCheckedClick() },
        thumbContent = {
            when(isChecked){
                true -> Box{Text(text = "Fa", color = MaterialTheme.colorScheme.onSurface)}
                else -> Box{Text(text = "En", color = MaterialTheme.colorScheme.onSurface)}
            }
        },
        colors = SwitchDefaults.colors(),
        modifier = modifier.padding(3.dp)
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