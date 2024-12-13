package com.hearity_capstone.hearity.ui.screens.testDetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hearity_capstone.hearity.data.model.testResult.TestResultModel
import com.hearity_capstone.hearity.ui.common.AppCard
import com.hearity_capstone.hearity.ui.common.AppCardSize
import com.hearity_capstone.hearity.ui.theme.PaddingExtraSmall
import com.hearity_capstone.hearity.ui.theme.PaddingSmall

@Composable
fun HearingLevelTable(testResult: TestResultModel, modifier: Modifier = Modifier) {

    val tableHeader = listOf("Frequency", "Left Ear Level", "Right Ear Level")


    val data = listOf(
        listOf(
            "500 Hz",
            "${testResult.leftFreq500Hz} dB",
            "${testResult.rightFreq500Hz} dB"
        ),
        listOf(
            "1000 Hz",
            "${testResult.leftFreq1000Hz} dB",
            "${testResult.rightFreq1000Hz} dB"
        ),
        listOf(
            "2000 Hz",
            "${testResult.leftFreq2000Hz} dB",
            "${testResult.rightFreq2000Hz} dB"
        ),
        listOf(
            "4000 Hz",
            "${testResult.leftFreq4000Hz} dB",
            "${testResult.rightFreq4000Hz} dB"
        ),
    )

    AppCard(
        modifier = modifier,
        size = AppCardSize.LARGE,
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(
                alpha = 0.5f
            ),
            disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .height(160.dp)
                    .clip(MaterialTheme.shapes.large)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainerLowest.copy(
                            alpha = 0.5f
                        )
                    )
                    .padding(PaddingSmall),
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center
            ) {
                // Header
                tableHeader.forEach { header ->
                    item {
                        Text(
                            text = header,
                            modifier = Modifier.padding(PaddingSmall),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }

                }
                data.flatten().forEach { cellData ->
                    item {
                        Text(
                            text = cellData,
                            modifier = Modifier.padding(
                                horizontal = PaddingSmall,
                                vertical = PaddingExtraSmall
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}