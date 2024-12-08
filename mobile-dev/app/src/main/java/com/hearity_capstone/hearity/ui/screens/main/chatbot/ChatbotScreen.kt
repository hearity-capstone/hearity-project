package com.hearity_capstone.hearity.ui.screens.main.chatbot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.hearity_capstone.hearity.R
import com.hearity_capstone.hearity.data.model.ChatMessageModel
import com.hearity_capstone.hearity.ui.screens.main.chatbot.components.BotResponse
import com.hearity_capstone.hearity.ui.screens.main.chatbot.components.Header
import com.hearity_capstone.hearity.ui.screens.main.chatbot.components.MessageInputField
import com.hearity_capstone.hearity.ui.screens.main.chatbot.components.UserMessages
import com.hearity_capstone.hearity.ui.theme.IconSizeExtraLarge
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.PaddingSmall
import com.hearity_capstone.hearity.ui.theme.SpacingSection
import kotlinx.coroutines.delay


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChatbotScreen(viewModel: ChatbotViewModel) {
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

    Column(Modifier.fillMaxSize()) {
        Header(
            Modifier.padding(horizontal = PaddingMedium),
            onClearClick = { viewModel.clearChat() })
        Column(
            modifier = Modifier
                .positionAwareImePadding()
                .verticalScroll(scrollState)
                .padding(horizontal = PaddingMedium)
                .weight(1f)
        ) {
            if (viewModel.messageList.isEmpty()) {
                Box(Modifier
                    .fillMaxWidth()
                    .weight(1f), contentAlignment = Alignment.Center) {
                    EmptyChat()
                }
            } else {
                MessageList(modifier = Modifier.weight(1f), messageList = viewModel.messageList)
            }
        }
        MessageInputField(
            modifier = Modifier.positionAwareImePadding(),
            message = message,
            onMessageChange = { v -> message = v },
            onSendClick = { viewModel.sendMessage(message) },
            context = LocalContext.current
        )
    }
}

@Composable
private fun MessageList(modifier: Modifier = Modifier, messageList: List<ChatMessageModel>) {
    Box(
        modifier = modifier,
    ) {
        LazyColumn(
            modifier = modifier.padding(vertical = PaddingSmall),
            reverseLayout = true
        ) {
            items(messageList.reversed()) {
                MessageRow(messageModel = it)
            }
        }
    }
}

@Composable
private fun EmptyChat(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(IconSizeExtraLarge),
            painter = painterResource(R.drawable.ic_chat_bubble_filled),
            contentDescription = "Chat Icon",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Spacer(Modifier.height(SpacingSection))
        Text(
            "Ask Hearity ..",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun MessageRow(messageModel: ChatMessageModel) {
    val isModel = messageModel.role == "model"

    if (isModel) {
        BotResponse(message = messageModel.message)
    } else {
        UserMessages(message = messageModel.message)
    }
}

// Custom ime padding
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

