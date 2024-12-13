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
import androidx.compose.ui.text.style.TextAlign
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.PaddingSmall
import com.hearity_capstone.hearity.util.TestResultUtils


@Composable
fun HearingSeverityChip(modifier: Modifier, hearingLevel: Float) {
    Box(modifier, contentAlignment = Alignment.CenterEnd) {
        Box(
            modifier = Modifier
                .background(
                    color = TestResultUtils.ClassifyHearingLevelDiagnosis.color(hearingLevel),
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
                text = TestResultUtils.ClassifyHearingLevelDiagnosis.string(hearingLevel),
                style = MaterialTheme.typography.labelSmall,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
        }
    }
}
