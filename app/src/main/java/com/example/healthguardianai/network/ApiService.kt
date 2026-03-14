package com.example.healthguardianai.network

import com.example.healthguardianai.models.*
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST("scan")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): ScanResponse


    @POST("ask")
    suspend fun askQuestion(
        @Body request: QuestionRequest
    ): AnswerResponse


    @POST("explain")
    suspend fun explainDisease(
        @Body request: Map<String, String>
    ): ExplainResponse


    // 🔹 AI Chat API
    @POST("v1/chat/completions")
    suspend fun getResponse(
        @Body chatRequest: ChatRequest
    ): ChatResponse
}