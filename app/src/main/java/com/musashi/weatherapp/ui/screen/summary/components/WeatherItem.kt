package com.musashi.weatherapp.ui.screen.summary.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherItem(
    modifier: Modifier = Modifier,
    cardColor: List<Color>,
    title: String,
    @DrawableRes image: Int,
    value: String,
    unit: String,
    offsetMillis: Int
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
            Box(modifier = Modifier.background(brush = Brush.verticalGradient(
                colors = cardColor
            ))) {
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
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
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

                    Image(
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
                        Text(
                            text = value,
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                        )
                        Text(
                            text = unit,
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                        )
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
            image = R.drawable.snow_fall,
            title = "title",
            value = "value",
            unit = "unit",
            offsetMillis = 5)
    }
    
}