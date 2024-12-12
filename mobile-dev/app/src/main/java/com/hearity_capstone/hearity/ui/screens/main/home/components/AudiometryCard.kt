package com.hearity_capstone.hearity.ui.screens.main.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hearity_capstone.hearity.ui.common.audiometryGraph.AudiometryGraph
import com.hearity_capstone.hearity.viewModel.TestResultViewModel

@Composable
fun AudiometryCard(testResultViewModel: TestResultViewModel) {
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
        AudiometryGraph(testResultViewModel)
    }
}