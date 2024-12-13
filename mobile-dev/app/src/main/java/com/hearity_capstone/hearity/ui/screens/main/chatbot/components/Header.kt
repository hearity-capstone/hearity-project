package com.hearity_capstone.hearity.ui.screens.main.chatbot.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.hearity_capstone.hearity.ui.theme.SpacingItem


@Composable
fun Header(modifier: Modifier = Modifier, onClearClick: () -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "HeaRity Chatbot", color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        TextButton(
            onClick = { onClearClick() }
        ) {
            Text("Clear Chats", color = MaterialTheme.colorScheme.onSurface)
            Spacer(Modifier.width(SpacingItem))
            Icon(imageVector = Icons.Outlined.DeleteOutline, contentDescription = "Add")
        }
    }
}