package com.musashi.weatherapp.ui.screen.navgraph.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherBottomBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
    ) {
    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f),
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                    )
                )
            ),
        containerColor = Color.Transparent,
        tonalElevation = 5.dp
    ) {
        items.forEachIndexed { index, item ->

                NavigationBarItem(
                    selected = index == selected,
                    onClick = { onItemClick(index) },
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = "",
                                modifier = Modifier.size(30.dp)
                                )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.4f),
                    ),
                    label = {
                        when(selected==index){
                            true -> Text(
                                text = item.text,
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                )
                            else -> Text(
                                text = item.text,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                    }
                )
        }
    }
}

data class BottomNavigationItem(
    val icon: ImageVector,
    val text: String
)

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview
@Composable
private fun WeatherBottomBarPreview() {
    WeatherAppTheme {
        Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            WeatherBottomBar(
                items = listOf(
                    BottomNavigationItem(icon = Icons.Outlined.Home, text = "Summary"),
                    BottomNavigationItem(icon = Icons.Default.Search, text = "Details"),
                ),
                selected = 0,
                onItemClick = {})
        }
    }
}
