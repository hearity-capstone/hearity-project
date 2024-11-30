package com.hearity_capstone.hearity.ui.screens.main.home.components.audiometryGraph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hearity_capstone.hearity.ui.theme.IconSizeExtraSmall
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.PaddingSmall
import com.hearity_capstone.hearity.ui.theme.SlateBlue
import com.hearity_capstone.hearity.ui.theme.SpacingMedium
import com.hearity_capstone.hearity.ui.theme.TomatoRed
import com.patrykandpatrick.vico.compose.axis.axisLineComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.compose.legend.horizontalLegend
import com.patrykandpatrick.vico.compose.legend.legendItem
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.dimensions.MutableDimensions
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry

@Composable
fun AudiometryGraph() {
    val modelProducer = remember { ChartEntryModelProducer() }
    val selectedEarSide = remember { mutableStateOf(EarSide.LEFT) }

    val leftEarData = remember {
        mutableStateListOf(
            listOf(
                FloatEntry(x = 0f, y = 30f),
                FloatEntry(x = 1f, y = 20f),
                FloatEntry(x = 2f, y = 35f),
                FloatEntry(x = 3f, y = 37f),
            )
        )
    }

    val rightEarData = remember {
        mutableStateListOf(
            listOf(
                FloatEntry(x = 0f, y = 60f),
                FloatEntry(x = 1f, y = 40f),
                FloatEntry(x = 2f, y = 55f),
                FloatEntry(x = 3f, y = 45f),
            )
        )
    }

    val datasetLineSpec = remember(selectedEarSide.value) {
        when (selectedEarSide.value) {
            EarSide.LEFT -> listOf(
                LineChart.LineSpec(
                    lineColor = LeftEarColor.toArgb(),
                    lineThicknessDp = 3f,
                    lineBackgroundShader = DynamicShaders.fromBrush(
                        brush = Brush.verticalGradient(
                            listOf(LeftEarColor.copy(alpha = 0.3f), LeftEarColor.copy(alpha = 0f))
                        )
                    ),

                )
            )

            EarSide.RIGHT -> listOf(
                LineChart.LineSpec(
                    lineColor = RightEarColor.toArgb(),
                    lineThicknessDp = 3f,
                    lineBackgroundShader = DynamicShaders.fromBrush(
                        brush = Brush.verticalGradient(
                            listOf(RightEarColor.copy(alpha = 0.3f), RightEarColor.copy(alpha = 0f))
                        )
                    )
                )
            )

            else -> listOf(
                LineChart.LineSpec(
                    lineColor = LeftEarColor.toArgb(),
                    lineThicknessDp = 3f,
                ),
                LineChart.LineSpec(
                    lineColor = RightEarColor.toArgb(),
                    lineThicknessDp = 3f,
                )
            )
        }
    }

    val scrollState = rememberChartScrollState()


    modelProducer.setEntries(
        when (selectedEarSide.value) {
            EarSide.LEFT -> leftEarData
            EarSide.RIGHT -> rightEarData
            EarSide.BOTH -> leftEarData + rightEarData
        }
    )


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(245.dp),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            EarDropdown(
                onSelected = { v -> selectedEarSide.value = v },
                selected = selectedEarSide.value
            )
        }
        if (leftEarData.isNotEmpty() || rightEarData.isEmpty()) {
            ProvideChartStyle {
                Chart(
                    modifier = Modifier.padding(
                        start = PaddingMedium,
                        end = PaddingMedium,
                        bottom = PaddingMedium
                    ),
                    chart = LineChart(
                        lines = datasetLineSpec,
                    ),
                    startAxis = rememberStartAxis(
                        title = "Hearing level (db)",
                        titleComponent = textComponent(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textSize = 10.sp,
                        ),
                        label = textComponent(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            padding = MutableDimensions(8f, 4f),
                            textSize = 12.sp,
                        ),
                        tickLength = 0.dp,
                        valueFormatter = { value, _ ->
                            value.toInt().toString()
                        },
                        axis = axisLineComponent(
                            strokeWidth = 2.dp,
                            strokeColor = MaterialTheme.colorScheme.outlineVariant
                        ),
                        tick = lineComponent(color = androidx.compose.ui.graphics.Color.Transparent),
                        itemPlacer = AxisItemPlacer.Vertical.default(
                            maxItemCount = 6
                        ),
                    ),
                    bottomAxis = rememberBottomAxis(
                        title = "Frequency (Hz)",
                        titleComponent = textComponent(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textSize = 10.sp,
                            padding = MutableDimensions(
                                topDp = 8f,
                                bottomDp = 0f,
                                startDp = 0f,
                                endDp = 0f
                            )
                        ),
                        label = textComponent(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            padding = MutableDimensions(8f, 4f),
                            textSize = 12.sp,
                        ),
                        tickLength = 0.dp,
                        valueFormatter = { value, _ ->
                            when (value.toInt()) {
                                0 -> "500"
                                1 -> "1k"
                                2 -> "2k"
                                3 -> "4k"
                                else -> ""
                            }
                        },
                        axis = axisLineComponent(
                            strokeWidth = 2.dp,
                            strokeColor = MaterialTheme.colorScheme.outlineVariant
                        ),
                        tick = lineComponent(color = androidx.compose.ui.graphics.Color.Transparent),
                        guideline = null
                    ),
                    chartModelProducer = modelProducer,
                    chartScrollState = scrollState,
                    isZoomEnabled = true,
                    marker = rememberMarker()
                    // legend = rememberLegend(),
                )
            }
        }
    }
}

// Legend
@Composable
private fun rememberLegend() = horizontalLegend(
    items = chartColors.mapIndexed { index, chartColor ->
        legendItem(
            icon = shapeComponent(Shapes.pillShape, chartColor),
            label = textComponent(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textSize = 10.sp,
            ),
            labelText = if (index == 0) "Left" else "Right"
        )
    },
    iconSize = legendItemIconSize,
    iconPadding = legendItemIconPaddingValue,
    spacing = legendItemSpacing,
    padding = legendPadding,
)

enum class EarSide {
    LEFT, RIGHT, BOTH
}

private val LeftEarColor = SlateBlue
private val RightEarColor = TomatoRed
private val chartColors = listOf(LeftEarColor, RightEarColor)

private val legendItemIconSize = IconSizeExtraSmall
private val legendItemIconPaddingValue = PaddingSmall
private val legendItemSpacing = SpacingMedium
private val legendTopPaddingValue = PaddingSmall
private val legendPadding = dimensionsOf(top = legendTopPaddingValue)