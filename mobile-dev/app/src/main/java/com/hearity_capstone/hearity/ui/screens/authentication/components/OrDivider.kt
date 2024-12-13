package com.hearity_capstone.hearity.ui.screens.authentication.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hearity_capstone.hearity.ui.theme.SpacingItem

@Composable
fun OrDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            Modifier.weight(1f),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
        Spacer(Modifier.width(SpacingItem))
        Text("Or", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.width(SpacingItem))
        HorizontalDivider(
            Modifier.weight(1f), thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    }
}
