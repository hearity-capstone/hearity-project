package com.hearity_capstone.hearity.ui.common.testResultCard

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hearity_capstone.hearity.data.model.TestResultModel
import com.hearity_capstone.hearity.ui.common.AppButton
import com.hearity_capstone.hearity.ui.common.AppButtonSize
import com.hearity_capstone.hearity.ui.common.AppCard
import com.hearity_capstone.hearity.ui.common.AppCardSize
import com.hearity_capstone.hearity.ui.theme.IconSizeSmall
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.ui.theme.SpacingSection
import com.hearity_capstone.hearity.util.DateUtils


@Composable
fun TestResultCard(testResult: TestResultModel, onSeeDetailsClick: () -> Unit = {}) {
    var isExpanded by remember { mutableStateOf(false) }



    AppCard(
        size = AppCardSize.MEDIUM,
        onClick = { isExpanded = !isExpanded },
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .animateContentSize()
        ) {
            // Header
            Row {
                Column {
                    Text(
                        text = DateUtils.formatLocalDate(testResult.date),
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
                            text = testResult.doctorName,
                            Modifier.width(200.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
                IconButton(
                    onClick = { isExpanded = !isExpanded }
                ) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                        contentDescription = "Expand More",
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
                        testResult.summary,
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(SpacingSection))
                    HearingLevelSummaryTable(testResult = testResult)
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

