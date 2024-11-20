package com.notfound.hearity.ui.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notfound.hearity.ui.theme.IconSizeExtraSmall
import com.notfound.hearity.ui.theme.PaddingMedium
import com.notfound.hearity.ui.theme.PaddingSmall
import com.notfound.hearity.ui.theme.SlateBlue
import com.notfound.hearity.ui.theme.SpacingMedium
import com.notfound.hearity.ui.theme.TomatoRed
import com.patrykandpatrick.vico.compose.axis.axisLineComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.compose.legend.horizontalLegend
import com.patrykandpatrick.vico.compose.legend.legendItem
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.dimensions.MutableDimensions
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry

@Composable
fun AudiometryGraph() {
    val modelProducer = remember { ChartEntryModelProducer() }

    val dataset1 = remember {
        mutableStateListOf(
            listOf(
                FloatEntry(x = 0f, y = 40f),
                FloatEntry(x = 1f, y = 50f),
                FloatEntry(x = 2f, y = 25f),
                FloatEntry(x = 3f, y = 45f)
            )
        )
    }

    val dataset2 = remember {
        mutableStateListOf(
            listOf(
                FloatEntry(x = 0f, y = 60f),
                FloatEntry(x = 1f, y = 40f),
                FloatEntry(x = 2f, y = 55f),
                FloatEntry(x = 3f, y = 35f)
            )
        )
    }

    val datasetLineSpec = remember {
        arrayListOf(
            LineChart.LineSpec(
                lineColor = color1.toArgb(),
                lineThicknessDp = 4.0f,
            ),
            LineChart.LineSpec(
                lineColor = color2.toArgb(),
                lineThicknessDp = 4.0f,
            )
        )
    }

    val scrollState = rememberChartScrollState()

    modelProducer.setEntries(dataset1 + dataset2)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
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
            FreqDropdown()
        }
        if (dataset1.isNotEmpty() || dataset2.isEmpty()) {
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
                        title = "Hearing loss level (db)",
                        titleComponent = textComponent(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textSize = 10.sp,
                        ),
                        label = textComponent(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            padding = MutableDimensions(8f,4f),
                            textSize = 10.sp,
                        ),
                        tickLength = 0.dp,
                        valueFormatter = { value, _ ->
                            value.toInt().toString()
                        },
                        axis = axisLineComponent(
                            strokeWidth = 2.dp,
                            strokeColor = MaterialTheme.colorScheme.outlineVariant
                        ),
                        tick = lineComponent(color = Color.Transparent),
                        itemPlacer = AxisItemPlacer.Vertical.default(
                            maxItemCount = 6
                        ),
                        guideline = null
                    ),
                    bottomAxis = rememberBottomAxis(
                        title = "Frequency (Hz)",
                        titleComponent = textComponent(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textSize = 10.sp,
                        ),
                        label = textComponent(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            padding = MutableDimensions(8f,4f),
                            textSize = 10.sp,
                        ),
                        tickLength = 0.dp,
                        valueFormatter = { value, _ ->
                            ((value.toInt()) + 1).toString()
                        },
                        axis = axisLineComponent(
                            strokeWidth = 2.dp,
                            strokeColor = MaterialTheme.colorScheme.outlineVariant
                        ),
                        tick = lineComponent(color = Color.Transparent),
                        guideline = null
                    ),

                    chartModelProducer = modelProducer,
                    chartScrollState = scrollState,
                    isZoomEnabled = true,
                    //  legend = rememberLegend(),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreqDropdown() {
    val isExpanded = remember { mutableStateOf(false) }
    val freq = remember { mutableStateOf("Left") }

    val freqOptions = listOf("Left", "Right", "Both")

    ExposedDropdownMenuBox(
        expanded = isExpanded.value,
        onExpandedChange = { isExpanded.value = it },
        modifier = Modifier
            .height(44.dp)
            .width(100.dp)
    ) {
        TextField(
            value = freq.value,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false },
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.surfaceBright,
                shape = MaterialTheme.shapes.medium
            )
        ) {
            freqOptions.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        freq.value = option
                        isExpanded.value = false
                    },
                    text = {
                        Text(
                            option,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                )
            }
        }
    }
}

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

private val color1 = TomatoRed
private val color2 = SlateBlue
private val chartColors = listOf(color1, color2)
private val legendItemIconSize = IconSizeExtraSmall
private val legendItemIconPaddingValue = PaddingSmall
private val legendItemSpacing = SpacingMedium
private val legendTopPaddingValue = PaddingSmall
private val legendPadding = dimensionsOf(top = legendTopPaddingValue)