package com.example.healthguardianai.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.healthguardianai.network.*

@Composable
fun SymptomCheckerScreen() {

    val scope = rememberCoroutineScope()

    var symptoms by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "AI Symptom Checker",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = symptoms,
            onValueChange = { symptoms = it },
            label = { Text("Enter your symptoms") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            scope.launch {

                val response = RetrofitClient.api.getResponse(
                    ChatRequest(
                        messages = listOf(
                            Message(
                                "user",
                                "User symptoms: $symptoms. Suggest possible causes and advice."
                            )
                        ),
                        model = TODO()
                    )
                )

                result = response.choices.first().message.content
            }

        }) {

            Text("Analyze")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(result)
    }
}