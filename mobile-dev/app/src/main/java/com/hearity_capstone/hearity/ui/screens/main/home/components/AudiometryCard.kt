package com.hearity_capstone.hearity.ui.screens.main.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hearity_capstone.hearity.ui.common.audiometryGraph.AudiometryGraph
import com.hearity_capstone.hearity.util.TestResultUtils
import com.hearity_capstone.hearity.viewModel.TestResultViewModel
import com.patrykandpatrick.vico.core.entry.FloatEntry

@Composable
fun AudiometryCard(testResultViewModel: TestResultViewModel) {
    val testResult by testResultViewModel.latestTestResult.collectAsState()

    val leftEarDataEntry = remember { mutableStateListOf<FloatEntry>() }
    val rightEarDataEntry = remember { mutableStateListOf<FloatEntry>() }

    LaunchedEffect(testResult) {
        leftEarDataEntry.clear()
        leftEarDataEntry.addAll(TestResultUtils.createLeftEarFloatEntries(testResult))

        rightEarDataEntry.clear()
        rightEarDataEntry.addAll(TestResultUtils.createRightEarFloatEntries(testResult))
    }

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
            testResult = testResult,
            leftEarDataEntry = leftEarDataEntry,
            rightEarDataEntry = rightEarDataEntry
        )
    }
}