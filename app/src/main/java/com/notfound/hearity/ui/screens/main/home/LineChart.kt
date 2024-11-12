package com.notfound.hearity.ui.screens.main.home

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.axis.axisLineComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry

@Composable
fun LineChart() {
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
                lineColor = Color.Blue.toArgb(),
                lineThicknessDp = 4.0f,
            ),
            LineChart.LineSpec(
                lineColor = Color.Red.toArgb(),
                lineThicknessDp = 4.0f,
            )
        )
    }

    val scrollState = rememberChartScrollState()

    modelProducer.setEntries(dataset1 + dataset2)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
        ,
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        FreqDropdown()
        if (dataset1.isNotEmpty() || dataset2.isEmpty()) {
            ProvideChartStyle {
                Chart(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    ),
                    chart = LineChart(
                        lines = datasetLineSpec,
                    ),
                    startAxis = rememberStartAxis(
                        title = "Top values",
                        tickLength = 0.dp,
                        valueFormatter = { value, _ ->
                            value.toInt().toString()
                        },
                        axis = axisLineComponent(
                            strokeWidth = 2.dp,
                            strokeColor = Color.LightGray
                        ),
                        tick = lineComponent(color = Color.Transparent),
                        itemPlacer = AxisItemPlacer.Vertical.default(
                            maxItemCount = 6
                        ),
                        guideline = null
                    ),
                    bottomAxis = rememberBottomAxis(
                        title = "Count of values",
                        tickLength = 0.dp,
                        valueFormatter = { value, _ ->
                            ((value.toInt()) + 1).toString()
                        },
                        axis = axisLineComponent(
                            strokeWidth = 2.dp,
                            strokeColor = Color.LightGray
                        ),

                        tick = lineComponent(color = Color.Transparent),
                        guideline = null
                    ),

                    chartModelProducer = modelProducer,
                    chartScrollState = scrollState,
                    isZoomEnabled = true
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreqDropdown() {
    val isExpanded = remember { mutableStateOf(false) }
    val freq = remember { mutableStateOf("1000 Hz") }

    val freqOptions = listOf("1000 Hz", "500 Hz", "250 Hz")

    ExposedDropdownMenuBox(
        expanded = isExpanded.value,
        onExpandedChange = { isExpanded.value = it },
        modifier = Modifier.height(46.dp)
    ) {
        TextField(
            value = freq.value,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.labelSmall,
            modifier = Modifier.menuAnchor().width(120.dp)
        )
        ExposedDropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false }
        ) {
            freqOptions.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        freq.value = option
                        isExpanded.value = false
                    },
                    text = { Text(option, style = MaterialTheme.typography.labelSmall) }
                )
            }
        }
    }
}
