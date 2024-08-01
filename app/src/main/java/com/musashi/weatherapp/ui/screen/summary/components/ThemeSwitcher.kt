package com.musashi.weatherapp.ui.screen.summary.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.activity.AppTheme
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun ThemeSwitcher(
    modifier: Modifier = Modifier,
    changeTheme: (AppTheme) -> Unit,
) {
    var isExpanded by rememberSaveable{ mutableStateOf(false) }
    Row(modifier = modifier) {
        AnimatedVisibility(
            visible = !isExpanded,
            exit = scaleOut(),
            enter = scaleIn()
        ) {
            Icon(
                imageVector = Icons.Default.ColorLens,
                contentDescription = "Yellow",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .clickable {
                        isExpanded = true
                    },
            )
        }
        AnimatedVisibility(
            visible = isExpanded,
            enter = slideInHorizontally(initialOffsetX = { w -> w }) + expandHorizontally(expandFrom = Alignment.End),
            exit = shrinkHorizontally()
        ) {
            Card(
                modifier = Modifier
                    .width(150.dp)
                    .height(30.dp),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.Circle,
                        contentDescription = "Yellow",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .clickable {
                                changeTheme(AppTheme.Yellow)
                                isExpanded = false
                            },
                        tint = Color.Yellow
                    )
                    Icon(
                        imageVector = Icons.Default.Circle,
                        contentDescription = "Red",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .clickable {
                                changeTheme(AppTheme.Red)
                                isExpanded = false
                            },
                        tint = Color.Red
                    )
                    Icon(
                        imageVector = Icons.Default.Circle,
                        contentDescription = "Blue",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .clickable {
                                changeTheme(AppTheme.Blue)
                                isExpanded = false
                            },
                        tint = Color.Blue
                    )
                    Icon(
                        imageVector = Icons.Default.Circle,
                        contentDescription = "Light",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .clickable {
                                changeTheme(AppTheme.Light)
                                isExpanded = false
                            },
                        tint = Color.Green
                    )
                    Icon(
                        imageVector = Icons.Default.Circle,
                        contentDescription = "Dark",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .clickable {
                                changeTheme(AppTheme.Dark)
                                isExpanded = false
                            },
                        tint = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ThemeSwitcherPreview() {
    WeatherAppTheme {
        ThemeSwitcher{}
    }
}