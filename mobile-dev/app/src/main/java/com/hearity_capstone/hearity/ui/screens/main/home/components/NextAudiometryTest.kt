package com.hearity_capstone.hearity.ui.screens.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.hearity_capstone.hearity.R
import com.hearity_capstone.hearity.ui.common.AppCard
import com.hearity_capstone.hearity.ui.common.AppCardSize
import com.hearity_capstone.hearity.ui.theme.IconContainerSizeMedium
import com.hearity_capstone.hearity.ui.theme.IconSizeMedium
import com.hearity_capstone.hearity.ui.theme.SpacingMedium
import com.hearity_capstone.hearity.ui.theme.SpacingSmall

@Composable
fun NextAudiometryTest(modifier: Modifier = Modifier) {
    AppCard(
        modifier = modifier,
        size = AppCardSize.LARGE,
        onClick = {},
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
    ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(IconContainerSizeMedium)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.75f)),
                    contentAlignment = Alignment.Center
                ) {
                   Image(
                       painter = painterResource(R.drawable.ic_event_filled),
                       contentDescription = "Next Audiometry Test Icon",
                       modifier = Modifier.size(IconSizeMedium),
                       colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondary),
                       alignment = Alignment.Center
                   )
                }
                Spacer(Modifier.height(SpacingMedium))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "15", style = MaterialTheme.typography.displayMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(Modifier.width(SpacingSmall))
                    Text(
                        "d", style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
                Spacer(Modifier.height(SpacingMedium))
                Text(
                    "Until Next Audiometry Test", style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
    }
}