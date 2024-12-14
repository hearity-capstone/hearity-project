package com.hearity_capstone.hearity.ui.screens.testDetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hearity_capstone.hearity.ui.common.AppCard
import com.hearity_capstone.hearity.ui.common.AppCardSize
import com.hearity_capstone.hearity.ui.theme.IconSizeLarge
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.SpacingItem


@Composable
fun HearingLevelInfoCard(modifier: Modifier = Modifier) {
    AppCard(
        modifier = modifier,
        size = AppCardSize.LARGE,
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 0.5f
            ),
            disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                modifier = Modifier.size(IconSizeLarge),
                imageVector = Icons.Outlined.Info,
                contentDescription = "Help icon",
                tint = MaterialTheme.colorScheme.tertiary
            )
            Spacer(Modifier.height(SpacingItem))
            Text(
                "<26 dB - Normal\n" +
                        "26 - 40 dB - Mild\n" +
                        "41 - 55 dB - Moderate\n" +
                        "56 - 70 dB - Moderately Severe\n" +
                        "71 - 90 dB - Severe\n" +
                        ">90 dB - Profound",
                modifier = Modifier.padding(horizontal = PaddingMedium),
                style = MaterialTheme.typography.labelSmall
            )
        }

    }
}