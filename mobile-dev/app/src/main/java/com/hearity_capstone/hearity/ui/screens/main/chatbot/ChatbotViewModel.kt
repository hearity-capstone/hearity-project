package com.hearity_capstone.hearity.ui.screens.main.chatbot

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.hearity_capstone.hearity.BuildConfig
import com.hearity_capstone.hearity.data.model.ChatMessageModel
import kotlinx.coroutines.launch

class ChatbotViewModel : ViewModel() {

    private val apiKey = BuildConfig.GEMINI_API_KEY
    private val keywords = listOf("hearing", "ear", "sound", "deafness", "hearing loss")

    val messageList by lazy {
        val suggestions = listOf(
            "What is Hearing Loss?",
            "What is Hearing Aid?",
            "How can I maintain good ear health?",
            "What are common causes of ear infections?",
            "When should I consult an audiologist?"
        )

        val randomSuggestion = suggestions.random()

        mutableStateListOf(
            ChatMessageModel(
                "Hello Welcome to HeaRity App, How can I help you today?\n" +
                        "Here is a suggestion for you: \n- $randomSuggestion",
                "model"
            )
        )
    }

    @SuppressLint("SecretInSource")
    val generativeModel: GenerativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = apiKey
    )

    private fun isRelevantToHearingHealth(response: String): Boolean {
        return keywords.any {
            response.contains(it, ignoreCase = true)
        }
    }

    @SuppressLint("NewApi")
    fun sendMessage(question: String) {
        viewModelScope.launch {
            try {
                val questionWithContext =
                    "answer only and about hearing health: $question"

                val chat = generativeModel.startChat(
                    history = messageList.map {
                        content(it.role) { text(it.message) }
                    }.toList()
                )

                messageList.add(ChatMessageModel(question, "user"))
                messageList.add(ChatMessageModel("Typing....", "model"))

                val response = chat.sendMessage(questionWithContext)

                val cleanResponse = response.text.toString().replace("**", "")

                if (!isRelevantToHearingHealth(cleanResponse)) {
                    messageList.removeLast()
                    messageList.add(
                        ChatMessageModel(
                            "Sorry, I can only answer questions about hearing health.",
                            "model"
                        )
                    )
                } else {
                    messageList.removeLast()
                    messageList.add(ChatMessageModel(cleanResponse, "model"))
                }

            } catch (e: Exception) {
                messageList.removeLast()
                messageList.add(ChatMessageModel("Error : " + e.message.toString(), "model"))
            }
        }
    }

    fun clearChat() {
        messageList.clear()

        val suggestions = listOf(
            "What is Hearing Loss?",
            "What is Hearing Aid?",
            "How can I maintain good ear health?",
            "What are common causes of ear infections?",
            "When should I consult an audiologist?"
        )

        val randomSuggestion = suggestions.random()

        messageList.add(
            ChatMessageModel(
                "Hello Welcome to HeaRity App, How can I help you today?\n" +
                        "Here is a suggestion for you: \n- $randomSuggestion",
                "model"
            )
        )
    }
}