package com.musashi.weatherapp.ui.screen.summary.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.common.LeftToRightLayout
import com.musashi.weatherapp.ui.common.shimmerEffect
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherItem(
    modifier: Modifier = Modifier,
    cardColor: List<Color>,
    shimmerColor: Color,
    title: String,
    @DrawableRes image: Int,
    value: String,
    unit: String,
    offsetMillis: Int,
    isWeatherLoaded: Boolean
) {

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val size by infiniteTransition.animateFloat(
        initialValue = 70f,
        targetValue = 60f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(
                offsetMillis = offsetMillis,
                offsetType = StartOffsetType.FastForward
            )
        ),
        label = ""
    )
    Column(
        modifier = modifier
            .width(100.dp)
            .height(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Card(
            modifier = Modifier
                .width(100.dp)
                .height(150.dp)
                .padding(4.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Box(
                modifier = Modifier.background(brush = Brush.verticalGradient(colors = cardColor)),
                contentAlignment = Alignment.TopCenter
            ) {
                Card(
                    modifier = Modifier
                        .width(90.dp)
                        .height(25.dp)
                        .padding(top = 5.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = title,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Black
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 30.dp)
                ) {
                    if(isWeatherLoaded) {
                        Icon(
                            painter = painterResource(id = image),
                            contentDescription = title,
                            modifier = Modifier.size(size.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier
                                .padding(bottom = 20.dp)
                                .fillMaxHeight()
                        ) {
                            if(LocalLayoutDirection.current == LayoutDirection.Rtl) {
                                LeftToRightLayout {
                                    Text(
                                        text = "$value $unit",
                                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                    )
                                }
                            }else
                                Text(
                                text = "$value $unit",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            )
                        }
                    }else{
                        Column {

                            Box(
                                modifier = Modifier
                                    .padding(bottom = 5.dp)
                                    .size(80.dp)
                                    .clip(shape = MaterialTheme.shapes.large)
                                    .shimmerEffect(
                                        color = shimmerColor
                                    )
                            )
                            Box(
                                modifier = Modifier
                                    .width(70.dp)
                                    .height(15.dp)
                                    .clip(shape = MaterialTheme.shapes.large)
                                    .shimmerEffect(
                                        color = shimmerColor,
                                        initialDelay = 20
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun WeatherItemPreview() {
    WeatherAppTheme {
        WeatherItem(
            cardColor = listOf(
                MaterialTheme.colorScheme.primaryContainer,
                MaterialTheme.colorScheme.secondaryContainer,
            ),
            shimmerColor = MaterialTheme.colorScheme.primaryContainer,
            image = R.drawable.snowfall_heavy,
            title = "title",
            value = "value",
            unit = "unit",
            offsetMillis = 5,
            isWeatherLoaded = false
        )
    }
    
}