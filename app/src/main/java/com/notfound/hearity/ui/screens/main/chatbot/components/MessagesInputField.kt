package com.notfound.hearity.ui.screens.main.chatbot.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.notfound.hearity.ui.theme.IconContainerSizeMedium
import com.notfound.hearity.ui.theme.IconSizeLarge
import com.notfound.hearity.ui.theme.PaddingMedium
import com.notfound.hearity.ui.theme.SpacingItem

@Composable
fun MessageInputField(
    message: String,
    onMessageChange: (String) -> Unit = {},
    context: Context
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(PaddingMedium),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            shape = MaterialTheme.shapes.large,
            value = message,
            onValueChange = onMessageChange,
            placeholder = { Text("Message ...", style = MaterialTheme.typography.bodyMedium) },
            modifier = Modifier.weight(1f),
        )
        Spacer(Modifier.width(SpacingItem))
        IconButton(
            colors = IconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            ),
            modifier = Modifier
                .size(IconContainerSizeMedium)
                .clickable { },
            onClick = {
                Toast.makeText(context, "This feature isn't ready yet!", Toast.LENGTH_SHORT).show()
            },
        ) {
            Icon(
                Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(IconSizeLarge)
                    .padding(2.dp)
            )
        }
    }
}