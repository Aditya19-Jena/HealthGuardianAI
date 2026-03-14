package com.example.healthguardianai.network

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

data class ChatRequest(
    val model: String,
    val messages: List<Message>
)

data class Message(
    val role: String,
    val content: String
)

data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)

interface OpenAIService {

    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer YOUR_API_KEY"
    )
    @POST("v1/chat/completions")
    suspend fun getResponse(
        @Body request: ChatRequest
    ): ChatResponse
}