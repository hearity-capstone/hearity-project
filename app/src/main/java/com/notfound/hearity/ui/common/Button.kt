package com.notfound.hearity.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    variant: AppButtonVariant = AppButtonVariant.Primary,
    onClick: () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    label: String = "Click",
    enabled: Boolean = true
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = MaterialTheme.shapes.large,
        colors = ButtonColors(
            containerColor = when (variant) {
                AppButtonVariant.Primary -> MaterialTheme.colorScheme.primary
                AppButtonVariant.Secondary -> MaterialTheme.colorScheme.secondary
                AppButtonVariant.Neutral -> MaterialTheme.colorScheme.surfaceContainerHighest
                else -> {
                    MaterialTheme.colorScheme.primary
                }
            },
            contentColor = when (variant) {
                AppButtonVariant.Primary -> MaterialTheme.colorScheme.onPrimary
                AppButtonVariant.Secondary -> MaterialTheme.colorScheme.onSecondary
                AppButtonVariant.Neutral -> MaterialTheme.colorScheme.onSurface
                else -> {
                    MaterialTheme.colorScheme.onPrimary
                }
            },
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
        ),
        onClick = onClick,
        enabled = enabled
    ) {
        if (leadingIcon != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                leadingIcon()
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        } else {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

open class AppButtonVariant {
    object Primary : AppButtonVariant()
    object Secondary : AppButtonVariant()
    object Neutral : AppButtonVariant()
}