package com.notfound.hearity.ui.screens.main.chatbot.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SmartToy
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.notfound.hearity.ui.theme.CornerSizeLarge
import com.notfound.hearity.ui.theme.CornerSizeMedium
import com.notfound.hearity.ui.theme.IconSizeMedium
import com.notfound.hearity.ui.theme.PaddingMedium
import com.notfound.hearity.ui.theme.PaddingSmall

@Composable
fun BotResponse(message: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(PaddingSmall),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.SmartToy,
                contentDescription = null,
                modifier = Modifier.size(IconSizeMedium),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(Modifier.width(PaddingSmall))
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = CornerSizeLarge,
                        topEnd = CornerSizeLarge,
                        bottomStart = CornerSizeMedium,
                        bottomEnd = CornerSizeLarge,
                    )
                )
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .padding(PaddingMedium),
            contentAlignment = Alignment.Center
        ) {
            Text(
                message,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}