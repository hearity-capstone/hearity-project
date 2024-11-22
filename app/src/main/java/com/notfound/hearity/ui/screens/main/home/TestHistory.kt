package com.notfound.hearity.ui.screens.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.notfound.hearity.R
import com.notfound.hearity.ui.theme.IconSizeMedium
import com.notfound.hearity.ui.theme.PaddingMedium
import com.notfound.hearity.ui.theme.SpacingItem
import com.notfound.hearity.ui.theme.SpacingLarge
import com.notfound.hearity.ui.theme.SpacingSmall
import com.notfound.hearity.ui.theme.TomatoRed
import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class HearingSeverity {
    NORMAL,
    WARNING
}

@Composable
fun TestHistory(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val testResults = listOf(
            TestResult(
                LocalDate.now(),
                "Dr. John Doe",
                "Normal hearing at low to medium frequencies. There is a mild hearing loss at high frequencies in the right ear.",
                HearingSeverity.NORMAL
            ),
            TestResult(
                LocalDate.now().minusDays(5), "Dr. Jane Smith",
                "There is a mild hearing loss at high frequencies in the right ear.",
                HearingSeverity.WARNING
            ),
            TestResult(
                LocalDate.now().minusDays(10), "Dr. Judy S.",
                "There is a mild hearing loss at high frequencies in the right ear.",
                HearingSeverity.WARNING
            )
        )

        testResults.forEach { result ->
            TestHistoryItem(result)
            Spacer(modifier = Modifier.height(SpacingItem))
        }
    }
}

@Composable
fun TestHistoryItem(result: TestResult) {
    val formattedDate = result.testDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .clickable { },
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(PaddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box {
                if (result.severity == HearingSeverity.WARNING) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_warning_outline),
                        contentDescription = null,
                        modifier = Modifier.size(IconSizeMedium),
                        colorFilter = ColorFilter.tint(TomatoRed)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_check_circle),
                        contentDescription = null,
                        modifier = Modifier.size(IconSizeMedium),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                }
            }
            Spacer(Modifier.width(SpacingLarge))
            Column {
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.height(SpacingSmall))
                Text(
                    text = result.doctorName,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(SpacingSmall))
                Text(
                    text = result.summary,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}

data class TestResult(
    val testDate: LocalDate,
    val doctorName: String,
    val summary: String,
    val severity: HearingSeverity
)
