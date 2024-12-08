package com.hearity_capstone.hearity.ui.screens.main.chatbot

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.hearity_capstone.hearity.data.model.ChatMessageModel
import kotlinx.coroutines.launch

class ChatbotViewModel : ViewModel() {

    private val keywords = listOf("hearing", "ear", "sound", "deafness", "hearing loss")
    private val greet =
        listOf("hello", "hi", "hey", "good morning", "good afternoon", "good evening")

    val messageList by lazy {
        mutableStateListOf<ChatMessageModel>()
    }

    @SuppressLint("SecretInSource")
    val generativeModel: GenerativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = "AIzaSyD4zxEarlkbdWlkOV-3Nx4ZqLdZ7ZVgvMY"
    )

    private fun isRelevantToHearingHealth(response: String): Boolean {
        return keywords.any {
            response.contains(it, ignoreCase = true)
        }
    }

    private fun isRelevantToGreetings(response: String): Boolean {
        return greet.any {
            response.contains(it, ignoreCase = true)
        }
    }

    @SuppressLint("NewApi")
    fun sendMessage(question: String) {
        viewModelScope.launch {
            try {
                val questionWithContext =
                    "answer only and about hearing health: $question"

                val greetings =
                    "answer the greetings like u are an chatbot for Hearity App that a service for hearing health and welcoming user to your app: $question"

                val chat = generativeModel.startChat(
                    history = messageList.map {
                        content(it.role) { text(it.message) }
                    }.toList()
                )

                messageList.add(ChatMessageModel(question, "user"))
                messageList.add(ChatMessageModel("Typing....", "model"))

                val response = chat.sendMessage(questionWithContext)
                val greeting = chat.sendMessage(greetings)

                val cleanResponse = response.text.toString().replace("**", "")
                val cleanGreetings = greeting.text.toString().replace("**", "")

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

                if (!isRelevantToGreetings(cleanGreetings)) {
                    messageList.removeLast()
                    messageList.add(
                        ChatMessageModel(
                            "Sorry, I cannot answer your greetings.",
                            "model"
                        )
                    )
                } else {
                    messageList.removeLast()
                    messageList.add(ChatMessageModel(cleanGreetings, "model"))
                }
            } catch (e: Exception) {
                messageList.removeLast()
                messageList.add(ChatMessageModel("Error : " + e.message.toString(), "model"))
            }
        }
    }

    fun clearChat() {
        messageList.clear()
    }
}