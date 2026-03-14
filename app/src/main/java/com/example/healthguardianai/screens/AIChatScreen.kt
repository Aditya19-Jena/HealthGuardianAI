package com.example.healthguardianai.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.launch

import com.example.healthguardianai.network.RetrofitClient
import com.example.healthguardianai.network.ChatRequest
import com.example.healthguardianai.network.Message

data class ChatMessage(
    val text: String,
    val isUser: Boolean
)

@Composable
fun AIChatScreen() {

    val scope = rememberCoroutineScope()

    var input by remember { mutableStateOf("") }

    val messages = remember { mutableStateListOf<ChatMessage>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121826))
            .padding(12.dp)
    ) {

        Text(
            text = "AI Health Assistant",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = false
        ) {

            items(messages) { message ->

                ChatBubble(message)
            }

        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = input,
                onValueChange = { input = it },
                placeholder = { Text("Ask a health question...") },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(

                onClick = {

                    if (input.isBlank()) return@IconButton

                    val userMessage = input

                    messages.add(ChatMessage(userMessage, true))

                    input = ""

                    scope.launch {

                        try {

                            val response = RetrofitClient.api.getResponse(

                                ChatRequest(
                                    model = "gpt-4o-mini",
                                    messages = listOf(
                                        Message(
                                            role = "user",
                                            content = userMessage
                                        )
                                    )
                                )

                            )

                            val aiText =
                                response.choices.firstOrNull()?.message?.content
                                    ?: "No response"

                            messages.add(ChatMessage(aiText, false))

                        } catch (e: Exception) {

                            messages.add(
                                ChatMessage(
                                    "Error contacting AI service",
                                    false
                                )
                            )
                        }

                    }

                }

            ) {

                Icon(
                    Icons.Default.Send,
                    contentDescription = "Send",
                    tint = Color(0xFF4ADE80)
                )
            }

        }

    }

}

@Composable
fun ChatBubble(message: ChatMessage) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
            if (message.isUser) Arrangement.End else Arrangement.Start
    ) {

        Card(
            colors = CardDefaults.cardColors(
                containerColor =
                    if (message.isUser)
                        Color(0xFF4ADE80)
                    else
                        Color(0xFF1E293B)
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(vertical = 6.dp)
                .widthIn(max = 280.dp)
        ) {

            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                color =
                    if (message.isUser)
                        Color.Black
                    else
                        Color.White
            )

        }

    }

}