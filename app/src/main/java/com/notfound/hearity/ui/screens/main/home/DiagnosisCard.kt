package com.notfound.hearity.ui.screens.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextOverflow
import com.notfound.hearity.R
import com.notfound.hearity.ui.theme.IconContainerSizeMedium
import com.notfound.hearity.ui.theme.IconSizeMedium
import com.notfound.hearity.ui.theme.PaddingMedium
import com.notfound.hearity.ui.theme.SpacingMedium

@Composable
fun DiagnosisCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.clip(MaterialTheme.shapes.extraLarge).clickable {  },
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingMedium)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(IconContainerSizeMedium)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.tertiary),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_stethoscope),
                        contentDescription = "stethoscope icon",
                        modifier = Modifier.size(IconSizeMedium),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiary),
                        alignment = Alignment.Center
                    )
                }
                Spacer(Modifier.width(SpacingMedium))
                Column {
                    Text("Diagnosis Result", style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ))

                    Spacer(Modifier.height(SpacingMedium))
                    Text("No diagnosis yet", style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary,
                    ), maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}
