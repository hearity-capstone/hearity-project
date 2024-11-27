package com.hearity_capstone.hearity.ui.screens.main.home.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hearity_capstone.hearity.ui.common.AppButton
import com.hearity_capstone.hearity.ui.common.AppButtonSize
import com.hearity_capstone.hearity.ui.theme.IconSizeSmall
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.PaddingSmall
import com.hearity_capstone.hearity.ui.theme.SeverityHigh
import com.hearity_capstone.hearity.ui.theme.SeverityLow
import com.hearity_capstone.hearity.ui.theme.SeverityModerate
import com.hearity_capstone.hearity.ui.theme.SeveritySevere
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.ui.theme.SpacingSection
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TestResultCard(result: TestResult, onSeeDetailsClick: () -> Unit = {}) {
    var isExpanded by remember { mutableStateOf(false) }
    val formattedDate = result.testDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .padding(PaddingMedium)
                .animateContentSize()
        ) {
            // Header
            Row {
                Column {
                    Text(
                        text = formattedDate,
                        style = MaterialTheme.typography.labelSmall,
                    )
                    Spacer(Modifier.height(SpacingSection))
                    Row {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = null,
                            modifier = Modifier.size(
                                IconSizeSmall
                            )
                        )
                        Spacer(Modifier.width(SpacingItem))
                        Text(
                            text = result.doctorName,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
                IconButton(
                    onClick = { isExpanded = !isExpanded },
                ) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                        contentDescription = "Expand More"
                    )
                }
            }

            // Expandable content
            if (isExpanded) {
                Column {
                    Spacer(Modifier.height(SpacingSection))
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant,
                        thickness = 1.dp
                    )
                    Spacer(Modifier.height(SpacingSection))
                    Text(
                        result.summary,
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(SpacingSection))
                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.large,
                        border = BorderStroke(
                            color = MaterialTheme.colorScheme.outlineVariant,
                            width = 1.dp
                        ),
                        colors = CardColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                    ) {
                        Column(Modifier.padding(PaddingMedium)) {
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
                                    "${result.leftEarLevel}dB",
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                HearingSeverityChip(Modifier.weight(1f), result.leftEarLevel)
                            }
                            Spacer(Modifier.height(SpacingItem))
                            HorizontalDivider(
                                color = MaterialTheme.colorScheme.outlineVariant,
                                thickness = 1.dp
                            )
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
                                    "${result.rightEarLevel}dB",
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                HearingSeverityChip(Modifier.weight(1f), result.rightEarLevel)
                            }
                        }
                    }
                    Spacer(Modifier.height(SpacingSection))

                    // Action Button
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        OutlinedButton(modifier = Modifier.weight(1f), onClick = {}) {
                            Icon(
                                modifier = Modifier.size(IconSizeSmall),
                                imageVector = Icons.Outlined.Share,
                                contentDescription = "share icon",
                            )
                            Spacer(Modifier.width(SpacingSection))
                            Text("Share", style = MaterialTheme.typography.bodyMedium)
                        }
                        Spacer(Modifier.width(SpacingSection))
                        AppButton(
                            modifier = Modifier.weight(1f),
                            label = "See Details",
                            onClick = { onSeeDetailsClick() },
                            size = AppButtonSize.Small
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HearingSeverityChip(modifier: Modifier, hearingLevel: Int) {
    Box(modifier, contentAlignment = Alignment.CenterEnd) {
        Box(
            modifier = Modifier
                .background(
                    color = when (hearingLevel) {
                        in 0..40 -> SeverityLow
                        in 41..60 -> SeverityModerate
                        in 61..90 -> SeverityHigh
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
                    in 0..40 -> "Low"
                    in 41..60 -> "Moderate"
                    in 61..90 -> "High"
                    else -> "Severe"
                },
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
        }
    }
}

data class TestResult(
    val id: Int,
    val testDate: LocalDate,
    val doctorName: String,
    val summary: String,
    val leftEarLevel: Int,
    val rightEarLevel: Int,
)
