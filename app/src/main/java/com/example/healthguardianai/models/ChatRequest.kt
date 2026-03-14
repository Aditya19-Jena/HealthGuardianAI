package com.example.healthguardianai.models

data class ChatRequest(
    val model: String,
    val messages: List<Message>
)