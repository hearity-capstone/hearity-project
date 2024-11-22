package com.notfound.hearity.ui.screens.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.notfound.hearity.R
import com.notfound.hearity.ui.theme.IconContainerSizeMedium
import com.notfound.hearity.ui.theme.IconSizeMedium
import com.notfound.hearity.ui.theme.PaddingMedium
import com.notfound.hearity.ui.theme.SpacingMedium
import com.notfound.hearity.ui.theme.SpacingSmall

@Composable
fun NextAppointmentCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.clip(MaterialTheme.shapes.extraLarge).clickable {  },
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingMedium)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(IconContainerSizeMedium)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondary),
                    contentAlignment = Alignment.Center
                ) {
                   Image(
                       painter = painterResource(R.drawable.ic_event_filled),
                       contentDescription = "event icon",
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
                    "Until Next Appointment", style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}