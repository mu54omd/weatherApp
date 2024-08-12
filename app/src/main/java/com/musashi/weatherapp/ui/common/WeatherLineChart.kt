package com.musashi.weatherapp.ui.common

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisGuidelineComponent
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisLabelComponent
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoScrollState
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.compose.common.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.common.data.rememberExtraLambda
import com.patrykandpatrick.vico.compose.common.fill
import com.patrykandpatrick.vico.compose.common.of
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.cartesian.layer.LineCartesianLayer
import com.patrykandpatrick.vico.core.common.Dimensions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
    val startAxisTitle = remember{ mutableIntStateOf(R.string.temperature) }
    val minValueIndex = remember { mutableIntStateOf(0) }
    val maxValueIndex = remember { mutableIntStateOf(0) }


    val bottomAxisValueFormatter = CartesianValueFormatter { x, _, _ ->
        xValues[x.toInt()]
    }
    LaunchedEffect(key1 = selectedCart.value, key2 = selectedDay.value) {
        withContext(Dispatchers.Default) {
            when(selectedCart.value) {

                chartOption[0] -> {
                    val dataPoints = dataGenerator(xValues, yValues, selectedDay.value)
                    startAxisTitle.intValue = R.string.temperature
                    minValueIndex.intValue = dataPoints.values.indexOf(dataPoints.values.min())
                    maxValueIndex.intValue = dataPoints.values.indexOf(dataPoints.values.max())
                    modelProducer.runTransaction {
                        lineSeries { series(dataPoints.values) }
                    }
                }
                chartOption[1] -> {
                    val dataPoints = dataGenerator(xValues, zValues, selectedDay.value)
                    startAxisTitle.intValue = R.string.humidity
                    minValueIndex.intValue = dataPoints.values.indexOf(dataPoints.values.min())
                    maxValueIndex.intValue = dataPoints.values.indexOf(dataPoints.values.max())
                    modelProducer.runTransaction {
                        lineSeries { series(dataPoints.values) }
                    }
                }
                chartOption[2] -> {
                    val dataPoints = dataGenerator(xValues, wValues, selectedDay.value)
                    minValueIndex.intValue = dataPoints.values.indexOf(dataPoints.values.min())
                    maxValueIndex.intValue = dataPoints.values.indexOf(dataPoints.values.max())
                    startAxisTitle.intValue = R.string.apparent_temp
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
            ChipsButton(
                onClick = { selectedDay.value = dayOption[0] },
                isSelected = selectedDay.value == dayOption[0],
                text = R.string.today
            )
            ChipsButton(
                onClick = { selectedDay.value = dayOption[1] },
                isSelected = selectedDay.value == dayOption[1],
                text = R.string.tomorrow
            )
            ChipsButton(
                onClick = { selectedDay.value = dayOption[2] },
                isSelected = selectedDay.value == dayOption[2],
                text = R.string.theـdayـafterـtomorrow
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            ChipsButton(
                onClick = { selectedCart.value = chartOption[0] },
                isSelected = selectedCart.value == chartOption[0],
                text = R.string.temperature
            )
            ChipsButton(
                onClick = { selectedCart.value = chartOption[1] },
                isSelected = selectedCart.value == chartOption[1],
                text = R.string.humidity
            )
            ChipsButton(
                onClick = { selectedCart.value = chartOption[2] },
                isSelected = selectedCart.value == chartOption[2],
                text = R.string.apparent_temp
            )
        }
        ComposeChart(
            modelProducer = modelProducer,
            modifier = modifier,
            bottomValueFormatter = bottomAxisValueFormatter,
            startAxisTitle = startAxisTitle.intValue,
            minValueIndex = minValueIndex.intValue,
            maxValueIndex = maxValueIndex.intValue
        )
    }
}

@Composable
private fun ComposeChart(
    modelProducer: CartesianChartModelProducer,
    modifier: Modifier,
    bottomValueFormatter: CartesianValueFormatter,
    @StringRes startAxisTitle: Int,
    minValueIndex: Int,
    maxValueIndex: Int,
    ) {
    val lineColor = MaterialTheme.colorScheme.primary
    val marker = rememberMarker()
    Log.d("TAG_min", "$minValueIndex")
    Log.d("TAG_max", "$maxValueIndex")
    CartesianChartHost(
        chart =
        rememberCartesianChart(
            rememberLineCartesianLayer(
                LineCartesianLayer.LineProvider.series(
                    rememberLine(remember { LineCartesianLayer.LineFill.single(fill(lineColor)) })
                ),
                pointSpacing = 2.dp
            ),
            startAxis = rememberStartAxis(
                guideline = rememberAxisGuidelineComponent(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                ),
                line = rememberLineComponent(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                label = rememberTextComponent(
                    color = MaterialTheme.colorScheme.onBackground,
                    ),
                labelRotationDegrees = 0f,
                title = stringResource(id = startAxisTitle),
                titleComponent = rememberTextComponent(
                    color = MaterialTheme.colorScheme.onBackground,
                    margins = Dimensions.of(top = 4.dp),
                    padding = Dimensions.of(8.dp, 2.dp),
                )
            ),
            bottomAxis = rememberBottomAxis(
                line = rememberLineComponent(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                guideline = null,
                valueFormatter = bottomValueFormatter,
                label = rememberAxisLabelComponent(
                    color = MaterialTheme.colorScheme.onBackground,
                ),
                labelRotationDegrees = -90f,
                title = stringResource(id = R.string.hour),
                titleComponent = rememberTextComponent(
                    color = MaterialTheme.colorScheme.onBackground,
                    margins = Dimensions.of(top = 4.dp),
                    padding = Dimensions.of(8.dp, 2.dp),
                ),
            ),
            marker = marker,
            persistentMarkers = rememberExtraLambda(rememberMarker()) {
                marker at(minValueIndex)
                marker at(maxValueIndex)
            },
        ),
        modelProducer = modelProducer,
        modifier = modifier,
        zoomState = rememberVicoZoomState(zoomEnabled = true),
        scrollState = rememberVicoScrollState(scrollEnabled = true)
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