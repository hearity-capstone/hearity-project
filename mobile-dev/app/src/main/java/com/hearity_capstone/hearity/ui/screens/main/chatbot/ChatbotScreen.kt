package com.hearity_capstone.hearity.ui.screens.main.chatbot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import com.hearity_capstone.hearity.ui.theme.PaddingSmall
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.ui.theme.SpacingSection

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

@Composable
fun ChatbotScreen() {
    var message by remember { mutableStateOf("") }

    Column {
        Column(
            modifier = Modifier
                .positionAwareImePadding()
                .weight(1f)
                .fillMaxWidth()
                .padding(top = SpacingItem),
            verticalArrangement = Arrangement.spacedBy(PaddingSmall)
        ) {
            Header(Modifier.padding(horizontal = PaddingMedium))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = PaddingMedium)
            ) {
                Spacer(Modifier.height(SpacingSection))
                UserMessages("This is an example of user's short message")
                Spacer(Modifier.height(SpacingItem))
                BotResponse("Hello! How can I help you today?")
                Spacer(Modifier.height(SpacingItem))
                UserMessages("This is an example of user's long message, which should wrap to multiple lines, demonstrating how the layout handles longer text inputs from the user.")
                Spacer(Modifier.height(SpacingItem))
                BotResponse("Okay, here's a longer response to demonstrate how the bot's messages can also wrap to multiple lines if they exceed the available width of the screen. This can be useful for providing detailed information or explanations to the user.")
                Spacer(Modifier.height(SpacingItem))
            }

            Spacer(Modifier.weight(1f))
            MessageInputField(
                message = message,
                onMessageChange = { v -> message = v },
                context = LocalContext.current
            )
        }
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
            "HeaRity Bot",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        TextButton(
            onClick = {}
        ) {
            Text("Clear Chats")
            Spacer(Modifier.width(SpacingItem))
            Icon(imageVector = Icons.Outlined.DeleteOutline, contentDescription = "Add")
        }
    }
}