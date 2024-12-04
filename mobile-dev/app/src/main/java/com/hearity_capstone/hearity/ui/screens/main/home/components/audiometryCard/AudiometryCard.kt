package com.hearity_capstone.hearity.ui.screens.main.home.components.audiometryCard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hearity_capstone.hearity.ui.common.audiometryGraph.AudiometryGraph
import com.hearity_capstone.hearity.ui.common.audiometryGraph.EarSide
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry

@Composable
fun AudiometryCard() {
    val modelProducer = remember { ChartEntryModelProducer() }
    val selectedEarSide = remember { mutableStateOf(EarSide.LEFT) }

    // Dummy data
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
        AudiometryGraph(
            modelProducer = modelProducer,
            selectedEarSide = selectedEarSide,
            leftEarData = leftEarData,
            rightEarData = rightEarData
        )
    }
}