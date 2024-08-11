package com.musashi.weatherapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.musashi.weatherapp.R
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.common.fill
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.cartesian.layer.LineCartesianLayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rememberMarker

@Composable
fun WeatherLineChart(
    modifier: Modifier = Modifier,
    xValues: List<String>,
    yValues: List<Double>,
    zValues: List<Double>,
    wValues: List<Double>,
) {
    val modelProducer = remember { CartesianChartModelProducer() }
    val chartOption = listOf("Temperature", "Humidity", "ApparentTemperature" )
    val dayOption = listOf("Today", "Tomorrow", "The Day After Tomorrow" )
    val selectedCart = rememberSaveable(chartOption) { mutableStateOf(chartOption[0]) }
    val selectedDay = rememberSaveable(dayOption) { mutableStateOf(dayOption[0]) }

    val bottomAxisValueFormatter = CartesianValueFormatter { x, _, _ ->
        xValues[x.toInt()]
    }
    LaunchedEffect(key1 = selectedCart.value, key2 = selectedDay.value) {
        withContext(Dispatchers.Default) {
            when(selectedCart.value) {

                chartOption[0] -> {
                    val dataPoints = dataGenerator(xValues, yValues, selectedDay.value)
                    modelProducer.runTransaction {
                        lineSeries { series(dataPoints.values) }
                    }
                }
                chartOption[1] -> {
                    val dataPoints = dataGenerator(xValues, zValues, selectedDay.value)
                    modelProducer.runTransaction {
                        lineSeries { series(dataPoints.values) }
                    }
                }
                chartOption[2] -> {
                    val dataPoints = dataGenerator(xValues, wValues, selectedDay.value)
                    modelProducer.runTransaction {
                        lineSeries { series(dataPoints.values) }
                    }
                }
            }
        }

    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(
                onClick = { selectedDay.value = dayOption[0] },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(selectedDay.value == dayOption[0]) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.surfaceContainer
                )
            ) {
                Text(text = stringResource(id = R.string.today))
            }
            TextButton(
                onClick = { selectedDay.value = dayOption[1] },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(selectedDay.value == dayOption[1]) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.surfaceContainer
                )
            ) {
                Text(text = stringResource(id = R.string.tomorrow))
            }
            TextButton(
                onClick = { selectedDay.value = dayOption[2] },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(selectedDay.value == dayOption[2]) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.surfaceContainer
                )
            ) {
                Text(text = stringResource(id = R.string.theـdayـafterـtomorrow))
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(
                onClick = { selectedCart.value = chartOption[0] },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(selectedCart.value == chartOption[0]) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceContainer
                )
            ) {
                Text(text = stringResource(id = R.string.temperature))
            }
            TextButton(
                onClick = { selectedCart.value = chartOption[1] },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(selectedCart.value == chartOption[1]) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceContainer
                )
            ) {
                Text(text = stringResource(id = R.string.humidity))
            }
            TextButton(
                onClick = { selectedCart.value = chartOption[2] },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(selectedCart.value == chartOption[2]) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceContainer
                )
            ) {
                Text(text = stringResource(id = R.string.apparent_temp))
            }
        }
        ComposeChart(
            modelProducer = modelProducer,
            modifier = modifier,
            bottomValueFormatter = bottomAxisValueFormatter
        )
    }
}

@Composable
private fun ComposeChart(
    modelProducer: CartesianChartModelProducer,
    modifier: Modifier,
    bottomValueFormatter: CartesianValueFormatter
    ) {

    CartesianChartHost(
        chart =
        rememberCartesianChart(
            rememberLineCartesianLayer(
                LineCartesianLayer.LineProvider.series(
                    rememberLine(remember { LineCartesianLayer.LineFill.single(fill(Color(0xffa485e0))) })
                )
            ),
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(
                guideline = null,
                valueFormatter = bottomValueFormatter
            ),
            marker = rememberMarker()
        ),
        modelProducer = modelProducer,
        modifier = modifier,
        zoomState = rememberVicoZoomState(zoomEnabled = true),
    )
}

private fun dataGenerator(xValues: List<String>, yValues: List<Double>, selectedDay: String):Map<String, Double>{
    val data = mutableMapOf<String, Double>()
    when(selectedDay) {
        "Today" -> {
            xValues.indices.forEach { index ->
                data[xValues[index]] = yValues[index]
            }
        }
        "Tomorrow" -> {
            xValues.indices.forEach { index ->
                data[xValues[index]] = yValues[index+23]
            }
        }
        "The Day After Tomorrow" -> {
            xValues.indices.forEach { index ->
                data[xValues[index]] = yValues[index+47]
            }
        }
    }
    return data
}