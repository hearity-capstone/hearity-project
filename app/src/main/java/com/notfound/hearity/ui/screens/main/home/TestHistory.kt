package com.notfound.hearity.ui.screens.main.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.notfound.hearity.ui.theme.PaddingSmall
import com.notfound.hearity.ui.theme.SpacingSmall
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
                "Normal hearing at low to medium frequencies. There is a mild hearing loss at high frequencies in the right ear."
            ),
            TestResult(
                LocalDate.now().minusDays(5), "Dr. Jane Smith",
                "There is a mild hearing loss at high frequencies in the right ear."
            ),
            TestResult(
                LocalDate.now().minusDays(10), "Dr. Judy S.",
                "There is a mild hearing loss at high frequencies in the right ear."
            )
        )

        testResults.forEach { result ->
            TestHistoryItem(result)
            Spacer(modifier = Modifier.height(SpacingSmall))
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(SpacingSmall))
        }
    }
}

@Composable
fun TestHistoryItem(result: TestResult) {
    val formattedDate = result.testDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(PaddingSmall)
    ) {
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
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

data class TestResult(
    val testDate: LocalDate,
    val doctorName: String,
    val summary: String
)
