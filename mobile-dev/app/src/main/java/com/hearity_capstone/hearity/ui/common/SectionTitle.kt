package com.hearity_capstone.hearity.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.hearity_capstone.hearity.ui.theme.IconSizeMedium
import com.hearity_capstone.hearity.ui.theme.IconSizeSmall
import com.hearity_capstone.hearity.ui.theme.SpacingMedium

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    actionTitle: String? = null,
    action: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon, contentDescription = null, tint =
                    MaterialTheme.colorScheme.primary, modifier = Modifier.size(IconSizeMedium)
            )
            Spacer(Modifier.width(SpacingMedium))
            Text(
                title, style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
        Row(
            modifier = Modifier.clickable {
                if (action != null) {
                    action()
                }
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (actionTitle != null) {
                Text(
                    actionTitle, style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.outline
                    )
                )
                Spacer(Modifier.width(SpacingMedium))
            }
            if (action != null) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    modifier = Modifier.size(
                        IconSizeSmall
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline
                )
            }
        }

    }
}