package com.hearity_capstone.hearity.ui.screens.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import com.hearity_capstone.hearity.R
import com.hearity_capstone.hearity.ui.common.AppCard
import com.hearity_capstone.hearity.ui.common.AppCardSize
import com.hearity_capstone.hearity.ui.theme.IconContainerSizeMedium
import com.hearity_capstone.hearity.ui.theme.IconSizeMedium
import com.hearity_capstone.hearity.ui.theme.SpacingMedium

@Composable
fun HearingEducationCard(modifier: Modifier = Modifier) {
    AppCard(
        modifier = modifier,
        size = AppCardSize.LARGE,
        onClick = { },
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(IconContainerSizeMedium)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.75f)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_school_outline),
                    contentDescription = "stethoscope icon",
                    modifier = Modifier.size(IconSizeMedium),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiary),
                    alignment = Alignment.Center
                )
            }
            Spacer(Modifier.width(SpacingMedium))
            Spacer(Modifier.height(SpacingMedium))
            Text(
                "Learn More About\nHearing Health",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
