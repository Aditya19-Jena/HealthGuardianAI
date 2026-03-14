package com.example.healthguardianai.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.healthguardianai.models.QuestionRequest
import com.example.healthguardianai.network.RetrofitClient
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AskQuestionScreen(
    navController: NavController,
    disease: String
) {

    val scope = rememberCoroutineScope()

    var question by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    val suggestedQuestion = "How can I treat $disease?"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ask AI Doctor") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = "Detected Condition: $disease",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                    question = suggestedQuestion

                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(suggestedQuestion)
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = question,
                onValueChange = { question = it },
                label = { Text("Ask your health question") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                    if (question.isNotBlank()) {

                        loading = true

                        scope.launch {

                            try {

                                val response =
                                    RetrofitClient.api.askQuestion(
                                        QuestionRequest(question)
                                    )

                                answer = response.answer

                            } catch (e: Exception) {

                                answer = "Error: ${e.message}"
                            }

                            loading = false
                        }
                    }

                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Ask AI")
            }

            Spacer(modifier = Modifier.height(30.dp))

            if (loading) {

                CircularProgressIndicator()

            }

            if (answer.isNotEmpty()) {

                Card {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "AI Response",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(answer)
                    }
                }
            }
        }
    }
}