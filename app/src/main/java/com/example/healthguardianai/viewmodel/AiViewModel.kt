package com.example.healthguardianai.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthguardianai.models.*
import com.example.healthguardianai.network.RetrofitClient
import kotlinx.coroutines.launch

class AiViewModel : ViewModel() {

    var answer: String = ""

    fun askQuestion(question: String, onResult: (String) -> Unit) {

        viewModelScope.launch {

            try {
                val response = RetrofitClient.api.askQuestion(
                    QuestionRequest(question)
                )

                answer = response.answer
                onResult(answer)

            } catch (e: Exception) {
                onResult("Error: ${e.message}")
            }
        }
    }
}