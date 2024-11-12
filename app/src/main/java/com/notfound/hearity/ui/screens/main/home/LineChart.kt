package com.notfound.hearity.ui.screens.main.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
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
    val datasetForModel = remember {
        mutableStateListOf(
            listOf(
                FloatEntry(x = 0f, y = 50f),
                FloatEntry(x = 1f, y = 50f),
                FloatEntry(x = 2f, y = 25f),
                FloatEntry(x = 3f, y = 45f)
            )
        )
    }
    val datasetLineSpec = remember {
        arrayListOf(
            LineChart.LineSpec(
                lineColor = Green.toArgb(),
                lineThicknessDp = 4.0f,
            )
        )
    }

    val scrollState = rememberChartScrollState()

    modelProducer.setEntries(datasetForModel)


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        if (datasetForModel.isNotEmpty()) {
            ProvideChartStyle {
                Chart(
                    modifier = Modifier.padding(16.dp),
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