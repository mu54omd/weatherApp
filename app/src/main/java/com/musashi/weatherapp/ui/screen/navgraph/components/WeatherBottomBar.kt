package com.musashi.weatherapp.ui.screen.navgraph.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherBottomBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
    ) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = Color.Yellow.copy(alpha = 0.5f),
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->

                NavigationBarItem(
                    selected = index == selected,
                    onClick = { onItemClick(index) },
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = item.icon),
                                contentDescription = "",
                                modifier = Modifier.size(50.dp),
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Red.copy(alpha = 0.3f),
                        selectedIconColor = Color.Blue
                    ),
                    label = {
                        when(selected==index){
                            true -> Text(text = item.text, style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),)
                            else -> Text(text = item.text, style = MaterialTheme.typography.bodySmall)
                        }

                    }
                )
        }
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Preview
@Composable
private fun WeatherBottomBarPreview() {
    WeatherAppTheme {
        Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            WeatherBottomBar(
                items = listOf(
                    BottomNavigationItem(icon = R.drawable.clear_sky, text = "Summary"),
                    BottomNavigationItem(icon = R.drawable.mainly_clear, text = "Details"),
                ),
                selected = 0,
                onItemClick = {})
        }
    }
}
