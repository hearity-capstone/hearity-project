package com.hearity_capstone.hearity.ui.screens.testDetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hearity_capstone.hearity.data.model.testResult.TestResultModel
import com.hearity_capstone.hearity.ui.common.AppCard
import com.hearity_capstone.hearity.ui.common.AppCardSize
import com.hearity_capstone.hearity.ui.theme.IconSizeMedium
import com.hearity_capstone.hearity.ui.theme.IconSizeSmall
import com.hearity_capstone.hearity.ui.theme.PaddingExtraSmall
import com.hearity_capstone.hearity.ui.theme.SpacingItem

@Composable
fun OverviewCard(testResult: TestResultModel) {
    AppCard(
        size = AppCardSize.LARGE,
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Doctor name and address or hospital name
            Row {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = "Doctor Icon",
                        Modifier.size(IconSizeMedium)
                    )
                    Spacer(Modifier.width(SpacingItem))

                    Column {
                        Text(
                            testResult.doctor,
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            testResult.hospital,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
            Spacer(Modifier.height(SpacingItem))

            Spacer(Modifier.height(SpacingItem))
            HorizontalDivider()
            Spacer(Modifier.height(SpacingItem))
            Row(
                Modifier.padding(horizontal = PaddingExtraSmall),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(IconSizeSmall),
                    imageVector = Icons.Outlined.Event,
                    contentDescription = "Doctor Icon",
                )
                Spacer(Modifier.width(SpacingItem))
                Text(
                    testResult.date.value.toString(),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}