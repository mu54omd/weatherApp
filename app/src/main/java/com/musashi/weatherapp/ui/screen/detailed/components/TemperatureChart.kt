package com.musashi.weatherapp.ui.screen.detailed.components

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.musashi.weatherapp.ui.theme.WeatherAppTheme
import kotlin.math.round
import kotlin.math.roundToInt

@Composable
fun TemperatureChart(
    modifier: Modifier = Modifier,
    xValues: List<Double> = emptyList(),
    yValues: List<String> = emptyList(),
    graphColor: Color = Color.Red
) {
    val spacing = 100f
    val aspectRatio = 3/2f
    val density = LocalDensity.current
    val barColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f)


    val transparentGraphColor = remember { graphColor.copy(alpha = 0.5f) }
    val upperValue = remember(xValues) { (xValues.maxOfOrNull { it.plus(1) })?.roundToInt() ?: 0 }
    val lowerValue = remember(xValues) { (xValues.minOfOrNull { it })?.toInt() ?: 0 }

    val textPaint = remember {
        Paint().apply {
            color = android.graphics.Color.BLACK
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Spacer(
            modifier = Modifier
                .padding(8.dp)
                .aspectRatio(aspectRatio)
                .fillMaxSize()
                .drawWithCache {
                    onDrawBehind {
                        val barWidthPx = 1.sp.toPx()
//                        drawRect(
//                            color = barColor,
//                            style = Stroke(barWidthPx),
//                            topLeft = Offset(spacing, 0f),
//                            size = Size(width = size.width - 1.5f * spacing, height = size.height - spacing)
//                        )

                        val verticalLines = 7
                        val verticalSize = size.width / (verticalLines + 1)
                        repeat(verticalLines){ i ->
                            val startX = verticalSize * (i + 1)
                            drawLine(
                                color = barColor,
                                start = Offset(startX, 0f),
                                end = Offset(startX, size.height - spacing),
                                strokeWidth = barWidthPx
                            )
                        }
                        val horizontalLines = 4
                        val sectionSize = size.height / (horizontalLines + 1)
                        repeat(horizontalLines){ i ->
                            val startX = sectionSize * (i + 1)
                            drawLine(
                                color = barColor,
                                start = Offset(spacing, startX),
                                end = Offset(size.width - spacing/2, startX),
                                strokeWidth = barWidthPx
                            )
                        }

                        val xAxisSpace = (size.width - spacing) / xValues.size
                        xValues.indices.forEach { i ->
                            val yElement = yValues[i]
                            drawContext.canvas.nativeCanvas.apply {
                                rotate(-90f) {
                                    drawText(
                                        yElement,
                                        size.height - 5*spacing,
                                        i * xAxisSpace,
                                        textPaint
                                    )
                                }
                            }
                        }
                        val yAxisSpace = (upperValue - lowerValue) / 5f
                        (0..5).forEach { i ->
                            drawContext.canvas.nativeCanvas.apply {
                                drawText(
                                    round(lowerValue + yAxisSpace * i).toString(),
                                    30f,
                                    size.height - spacing - i * size.height / 5f,
                                    textPaint
                                )
                            }
                        }
                        var lastX = 0f
                        val strokePath = Path().apply {
                            val height = size.height
                            for (i in xValues.indices) {
                                val info = xValues[i]
                                val nextInfo = xValues.getOrNull(i + 1) ?: xValues.last()
                                val leftRatio = (info - lowerValue) / (upperValue - lowerValue)
                                val rightRatio = (nextInfo - lowerValue) / (upperValue - lowerValue)

                                val x1 = spacing + i * xAxisSpace
                                val y1 = height - spacing - (leftRatio * height).toFloat()
                                val x2 = spacing + (i + 1) * xAxisSpace
                                val y2 = height - spacing - (rightRatio * height).toFloat()
                                if (i == 0) {
                                    moveTo(x1, y1)
                                }
                                lastX = (x1 + x2) / 2f
                                quadraticTo(x1, y1, lastX, (y1 + y2) / 2f)
                            }
                        }
                        val fillPath = android.graphics.Path(strokePath.asAndroidPath())
                            .asComposePath()
                            .apply {
                                lineTo(lastX, size.height - spacing)
                                lineTo(spacing, size.height - spacing)
                                close()
                            }
                        drawPath(
                            path = fillPath,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    transparentGraphColor,
                                    Color.Transparent
                                ),
                                endY = size.height - spacing
                            )
                        )

                        drawPath(
                            path = strokePath,
                            color = graphColor,
                            style = Stroke(
                                width = 3.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                        )
                    }
                }
        )
    }
}
//    Box(modifier = modifier
//        .background(MaterialTheme.colorScheme.background)
//    ){
//        Spacer(modifier = Modifier
//            .padding(8.dp)
//            .aspectRatio(3/2f)
//            .fillMaxSize()
//            .drawWithCache {
//                val path = generatePath(xValues, yValue)
//
//                val fillPath = Path()
//                fillPath.addPath(path)
//                fillPath.lineTo(size.width, size.height)
//                fillPath.lineTo(0f, size.height)
//                fillPath.close()
//
//                onDrawBehind {

//

//                    drawPath(
//                        path = path,
//                        color = Color.Green,
//                        style = Stroke(2.dp.toPx()))
//                    drawPath(
//                        path = fillPath,
//                        brush = Brush.verticalGradient(listOf(transparentGraphColor, Color.Transparent)),
//                        style = Fill
//                    )
//                }
//            }
//        )
//    }

@Preview(showSystemUi = true)
@Composable
private fun GraphPreview() {
    WeatherAppTheme {
        TemperatureChart(
            xValues = listOf(10.4, 434.5, 232.4, 55.5, 34.3, 46.9, 34.5, 134.5, 304.5, 233.4),
            yValues = listOf("00:00","10:00","11:00","14:00","15:00","16:00","17:00","18:00","19:00","10:00")
        )
    }
}