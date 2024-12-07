package com.hearity_capstone.hearity.ui.common.testResultCard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hearity_capstone.hearity.data.model.TestResultModel
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.PaddingSmall
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.util.TestResultUtils

@Composable
fun HearingLevelSummaryTable(modifier: Modifier = Modifier, testResult: TestResultModel) {

    val testResultAverageLeftEarFrequency =
        TestResultUtils.calculateAverageLeftEarFrequency(testResult.earFrequency)
    val testResultAverageRightEarFrequency =
        TestResultUtils.calculateAverageRightEarFrequency(testResult.earFrequency)

    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(
            color = MaterialTheme.colorScheme.outlineVariant,
            width = 1.dp
        ),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest.copy(
                alpha = 0.5f
            ),
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
    ) {
        Column(Modifier.padding(PaddingMedium)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = PaddingSmall),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Ear",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    "Average Level",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Text(
                    "Severity",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

            }
            Spacer(Modifier.height(SpacingItem))
            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant,
            )
            Spacer(Modifier.height(SpacingItem))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Left Ear",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    "${testResultAverageLeftEarFrequency.toInt()}dB",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                HearingSeverityChip(
                    Modifier.weight(1f),
                    testResultAverageLeftEarFrequency
                )
            }
            Spacer(Modifier.height(SpacingItem))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Right Ear",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    "${testResultAverageRightEarFrequency.toInt()}dB",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                HearingSeverityChip(
                    Modifier.weight(1f),
                    testResultAverageRightEarFrequency
                )
            }
        }
    }
}