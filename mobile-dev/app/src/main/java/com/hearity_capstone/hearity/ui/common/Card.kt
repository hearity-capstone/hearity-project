package com.hearity_capstone.hearity.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.PaddingSmall

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    size: AppCardSize = AppCardSize.MEDIUM,
    colors: CardColors = CardColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ),
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .clip(
                when (size) {
                    AppCardSize.SMALL -> MaterialTheme.shapes.medium
                    AppCardSize.MEDIUM -> MaterialTheme.shapes.large
                    AppCardSize.LARGE -> MaterialTheme.shapes.extraLarge
                }
            )
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier),

        colors = colors
    ) {
        Box(
            modifier = Modifier
                .padding(
                    when (size) {
                        AppCardSize.SMALL -> PaddingSmall
                        AppCardSize.MEDIUM -> PaddingMedium
                        AppCardSize.LARGE -> PaddingMedium
                    }
                )

        ) {
            content()
        }
    }
}

enum class AppCardSize {
    SMALL, MEDIUM, LARGE
}