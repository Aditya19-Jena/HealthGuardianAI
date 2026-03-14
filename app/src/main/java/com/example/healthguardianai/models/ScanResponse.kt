package com.example.healthguardianai.models

data class ScanResponse(
    val disease: String,
    val confidence: Int,
    val description: String,
    val precautions: List<String>
)