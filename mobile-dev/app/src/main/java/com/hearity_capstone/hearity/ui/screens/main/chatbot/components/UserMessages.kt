package com.hearity_capstone.hearity.ui.screens.main.chatbot.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hearity_capstone.hearity.ui.theme.CornerSizeExtraLarge
import com.hearity_capstone.hearity.ui.theme.PaddingMedium

@Composable
fun UserMessages(message: String) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = CornerSizeExtraLarge,
                        topEnd = CornerSizeExtraLarge,
                        bottomStart = CornerSizeExtraLarge,
                        bottomEnd = 0.dp,
                    )
                )
                .clickable { }
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .padding(PaddingMedium),
            contentAlignment = Alignment.Center
        ) {
            Text(
                message,
                lineHeight = 30.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
