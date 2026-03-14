package com.example.healthguardianai.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.healthguardianai.network.*

@Composable
fun MedicalReportScreen() {

    val scope = rememberCoroutineScope()

    var report by remember { mutableStateOf("") }
    var explanation by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Medical Report Explainer",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = report,
            onValueChange = { report = it },
            label = { Text("Paste medical report") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            scope.launch {

                val response = RetrofitClient.api.getResponse(
                    ChatRequest(
                        messages = listOf(
                            Message(
                                "user",
                                "Explain this medical report in simple language: $report"
                            )
                        ),
                        model = TODO()
                    )
                )

                explanation = response.choices.first().message.content
            }

        }) {

            Text("Explain Report")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(explanation)
    }
}