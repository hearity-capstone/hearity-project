package com.hearity_capstone.hearity.ui.common.testResultCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.PaddingSmall
import com.hearity_capstone.hearity.ui.theme.SeverityHigh
import com.hearity_capstone.hearity.ui.theme.SeverityLow
import com.hearity_capstone.hearity.ui.theme.SeverityModerate
import com.hearity_capstone.hearity.ui.theme.SeveritySevere


@Composable
fun HearingSeverityChip(modifier: Modifier, hearingLevel: Float) {
    Box(modifier, contentAlignment = Alignment.CenterEnd) {
        Box(
            modifier = Modifier
                .background(
                    color = when (hearingLevel) {
                        in 0f..41f -> SeverityLow
                        in 41f..60f -> SeverityModerate
                        in 60f..90f -> SeverityHigh
                        else -> SeveritySevere
                    },
                    shape = CircleShape
                )
                .padding(
                    horizontal = PaddingMedium,
                    vertical = PaddingSmall
                )
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = when (hearingLevel) {
                    in 0f..41f -> "Low"
                    in 41f..60f -> "Moderate"
                    in 60f..90f -> "High"
                    else -> "Severe"
                },
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
        }
    }
}
