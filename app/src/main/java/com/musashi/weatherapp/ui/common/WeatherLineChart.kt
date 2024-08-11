package com.musashi.weatherapp.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.musashi.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherLineChart(
    modifier: Modifier = Modifier,
    xValues: List<String>,
    yValues: List<Double>,
) {
    val dataPoints = dataGenerator(xValues, yValues)


}

private fun dataGenerator(xValues: List<String>, yValues: List<Double>):Map<String, Double>{
    val data = mutableMapOf<String, Double>()
    xValues.indices.forEach { index ->
        data += xValues[index] to yValues[index]
    }
    return data
}

@Preview
@Composable
private fun WeatherLineChartPreview() {
    WeatherAppTheme {
        WeatherLineChart(
            xValues = listOf("11:00","11:00","11:00","11:00","11:00","11:00","11:00","11:00","11:00","11:00",),
            yValues = listOf(25.5, 25.3, 29.1, 24.9, 24.8, 24.9, 24.9, 25.8, 26.2, 26.9)
        )
    }
}