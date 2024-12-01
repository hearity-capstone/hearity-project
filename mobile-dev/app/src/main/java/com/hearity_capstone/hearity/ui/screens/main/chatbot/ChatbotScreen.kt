package com.hearity_capstone.hearity.ui.screens.main.chatbot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.hearity_capstone.hearity.ui.screens.main.chatbot.components.BotResponse
import com.hearity_capstone.hearity.ui.screens.main.chatbot.components.MessageInputField
import com.hearity_capstone.hearity.ui.screens.main.chatbot.components.UserMessages
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.ui.theme.SpacingSection
import kotlinx.coroutines.delay

fun Modifier.positionAwareImePadding() = composed {
    var consumePadding by remember { mutableIntStateOf(0) }
    onGloballyPositioned { coordinates ->
        val rootCoordinate = coordinates.findRootCoordinates()
        val bottom = coordinates.positionInWindow().y + coordinates.size.height
        consumePadding = (rootCoordinate.size.height - bottom).toInt()
    }
        .consumeWindowInsets(PaddingValues(bottom = (consumePadding / LocalDensity.current.density).dp))
        .imePadding()
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChatbotScreen() {
    var message by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    val isKeyboardVisible = WindowInsets.isImeVisible

    val lastImeState = remember { mutableStateOf(isKeyboardVisible) }

    LaunchedEffect(isKeyboardVisible) {
        if (isKeyboardVisible != lastImeState.value) {
            lastImeState.value = isKeyboardVisible
            if (isKeyboardVisible) {
                delay(200)
                scrollState.animateScrollTo(scrollState.maxValue)
            }
        }
    }

    Column(Modifier.fillMaxHeight()) {
        Header(Modifier.padding(horizontal = PaddingMedium))
        Column(
            modifier = Modifier
                .positionAwareImePadding()
                .verticalScroll(scrollState)
                .padding(horizontal = PaddingMedium)
                .weight(1f)
        ) {
            Spacer(Modifier.height(SpacingSection))
            UserMessages("Hello, I'm concerned about my ear health.")
            Spacer(Modifier.height(SpacingSection))
            BotResponse("I understand. Could you tell me more about your concerns?")
            Spacer(Modifier.height(SpacingSection))
            UserMessages("I've been experiencing some ringing in my ears and occasional dizziness. It's been happening for a few weeks now.")
            Spacer(Modifier.height(SpacingSection))
            BotResponse("Thank you for sharing that information. Ringing in the ears and dizziness can be symptoms of various conditions, including tinnitus and Meniere's disease. It's important to consult with a healthcare professional to get a proper diagnosis and discuss treatment options.")
            Spacer(Modifier.height(SpacingSection))
            UserMessages("Okay, I'll schedule an appointment with my doctor. Thanks for the advice.")
            Spacer(Modifier.height(SpacingSection))
            Spacer(Modifier.height(SpacingSection))
            BotResponse("You are welcome!.")
            Spacer(Modifier.height(SpacingSection))

        }
        MessageInputField(
            modifier = Modifier.positionAwareImePadding(),
            message = message,
            onMessageChange = { v -> message = v },
            context = LocalContext.current
        )
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "HeaRity Bot", color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        TextButton(
            onClick = {}
        ) {
            Text("Clear Chats", color = MaterialTheme.colorScheme.onSurface)
            Spacer(Modifier.width(SpacingItem))
            Icon(imageVector = Icons.Outlined.DeleteOutline, contentDescription = "Add")
        }
    }
}