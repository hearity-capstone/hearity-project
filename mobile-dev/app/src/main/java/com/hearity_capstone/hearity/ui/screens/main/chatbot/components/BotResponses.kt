package com.hearity_capstone.hearity.ui.screens.main.chatbot.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hearity_capstone.hearity.R
import com.hearity_capstone.hearity.ui.theme.IconSizeMedium
import com.hearity_capstone.hearity.ui.theme.PaddingExtraSmall
import com.hearity_capstone.hearity.ui.theme.PaddingSmall

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BotResponse(message: String) {
    val context = LocalContext.current
    val clipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    val clipData: ClipData = ClipData.newPlainText("hearity chatbot", message)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface),
                modifier = Modifier.size(IconSizeMedium)
            )
        }
        Spacer(Modifier.width(PaddingSmall))
        Box(
            Modifier
                .combinedClickable(
                    onClick = { },
                    onLongClick = { clipboardManager.setPrimaryClip(clipData) },
                )
                .padding(PaddingExtraSmall),
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