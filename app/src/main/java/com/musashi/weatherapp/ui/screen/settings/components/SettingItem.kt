package com.musashi.weatherapp.ui.screen.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    title: String,
    defaultIndex: Int,
    menuContent: List<String> = emptyList(),
    onMenuItemClick: (String) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(start = 30.dp, end = 30.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)
        SettingDropDownMenu(
            menuContent = menuContent,
            defaultIndex = defaultIndex,
            onMenuItemClick = { onMenuItemClick(it) }
        )
    }
}