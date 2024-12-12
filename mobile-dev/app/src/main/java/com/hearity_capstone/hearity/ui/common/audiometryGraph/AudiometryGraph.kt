package com.hearity_capstone.hearity.ui.common.audiometryGraph

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.hearity_capstone.hearity.R
import com.hearity_capstone.hearity.data.model.testResult.EarSide
import com.hearity_capstone.hearity.ui.theme.IconSizeExtraSmall
import com.hearity_capstone.hearity.ui.theme.IconSizeSmall
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.PaddingSmall
import com.hearity_capstone.hearity.ui.theme.SlateBlue
import com.hearity_capstone.hearity.ui.theme.SpacingMedium
import com.hearity_capstone.hearity.ui.theme.SpacingSmall
import com.hearity_capstone.hearity.ui.theme.TomatoRed
import com.hearity_capstone.hearity.util.TestResultUtils
import com.hearity_capstone.hearity.viewModel.TestResultViewModel
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
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.composed.ComposedChartEntryModelProducer

@Composable
fun AudiometryGraph(
    testResultViewModel: TestResultViewModel
) {
    val testResult by testResultViewModel.latestTestResult.collectAsState()

    val modelProducer = ComposedChartEntryModelProducer.build()
    val selectedEarSide = remember { mutableStateOf(EarSide.LEFT) }

    val scrollState = rememberChartScrollState()

    val leftEarDataEntry = remember { mutableStateListOf<FloatEntry>() }
    val rightEarDataEntry = remember { mutableStateListOf<FloatEntry>() }

    LaunchedEffect(testResult) {
        leftEarDataEntry.clear()
        leftEarDataEntry.addAll(TestResultUtils.createLeftEarFloatEntries(testResult))

        rightEarDataEntry.clear()
        rightEarDataEntry.addAll(TestResultUtils.createRightEarFloatEntries(testResult))
    }


    modelProducer.runTransaction {
        when (selectedEarSide.value) {
            EarSide.LEFT -> add(leftEarDataEntry)
            EarSide.RIGHT -> add(rightEarDataEntry)
            EarSide.BOTH -> {
                add(leftEarDataEntry)
                add(rightEarDataEntry)
            }
        }
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

    Column(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_event_filled),
                    modifier = Modifier.size(IconSizeSmall),
                    contentDescription = "Date icon",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
                )
                Spacer(Modifier.width(SpacingSmall))
                Text(
                    testResult?.date?.value.toString(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            EarDropdown(
                modifier = Modifier.align(Alignment.Center),
                onSelected = { v -> selectedEarSide.value = v },
                selected = selectedEarSide.value
            )
        }
        if (leftEarDataEntry.isNotEmpty() || rightEarDataEntry.isEmpty()) {
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
                            (value * (-1.0f)).toInt().toString()
                        },
                        axis = axisLineComponent(

                            strokeWidth = 2.dp,
                            strokeColor = MaterialTheme.colorScheme.outlineVariant
                        ),
                        tick = lineComponent(color = androidx.compose.ui.graphics.Color.Transparent),
                        itemPlacer = AxisItemPlacer.Vertical.default(
                            maxItemCount = 6,
                            shiftTopLines = false
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

private val LeftEarColor = SlateBlue
private val RightEarColor = TomatoRed
private val chartColors = listOf(LeftEarColor, RightEarColor)

private val legendItemIconSize = IconSizeExtraSmall
private val legendItemIconPaddingValue = PaddingSmall
private val legendItemSpacing = SpacingMedium
private val legendTopPaddingValue = PaddingSmall
private val legendPadding = dimensionsOf(top = legendTopPaddingValue)