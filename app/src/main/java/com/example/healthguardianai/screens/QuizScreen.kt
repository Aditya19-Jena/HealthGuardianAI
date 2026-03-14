package com.example.healthguardianai.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(navController: NavController) {

    val backgroundGradient = Brush.verticalGradient(
        listOf(
            Color(0xFF020617),
            Color(0xFF0F172A),
            Color(0xFF1E293B)
        )
    )

    val buttonGradient = Brush.horizontalGradient(
        listOf(
            Color(0xFF38BDF8),
            Color(0xFF22C55E)
        )
    )

    val quiz = listOf(
        QuizQuestion(
            "What is the best way to prevent skin infections?",
            listOf(
                "Keep skin clean",
                "Ignore wounds",
                "Share towels",
                "Avoid washing"
            ),
            0
        ),
        QuizQuestion(
            "What should you do if a rash spreads quickly?",
            listOf(
                "Ignore it",
                "Apply random cream",
                "Consult a doctor",
                "Scratch it"
            ),
            2
        )
    )

    var currentQuestion by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf(-1) }
    var score by remember { mutableStateOf(0) }
    var showResult by remember { mutableStateOf(false) }
    var quizFinished by remember { mutableStateOf(false) }

    val question = quiz[currentQuestion]

    Scaffold(

        containerColor = Color.Transparent,

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        "Health Awareness Quiz",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                },

                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )

            )
        },

        bottomBar = { BottomNavigationBar(navController) }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundGradient)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

            if (!quizFinished) {

                LinearProgressIndicator(
                    progress = (currentQuestion + 1) / quiz.size.toFloat(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = Color(0xFF38BDF8),
                    trackColor = Color(0xFF1E293B)
                )

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    "Question ${currentQuestion + 1}",
                    color = Color(0xFF38BDF8),
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    question.question,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(30.dp))

                question.options.forEachIndexed { index, option ->

                    val isCorrect = index == question.correctAnswer
                    val isSelected = index == selectedAnswer

                    val cardColor by animateColorAsState(
                        when {
                            showResult && isCorrect -> Color(0xFF065F46)
                            showResult && isSelected && !isCorrect -> Color(0xFF7F1D1D)
                            isSelected -> Color(0xFF1E40AF)
                            else -> Color(0xFF1E293B)
                        },
                        label = ""
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .shadow(10.dp, RoundedCornerShape(18.dp))
                            .clickable(enabled = !showResult) {

                                selectedAnswer = index
                                showResult = true

                                if (index == question.correctAnswer) {
                                    score++
                                }
                            },

                        colors = CardDefaults.cardColors(containerColor = cardColor),

                        shape = RoundedCornerShape(18.dp)

                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),

                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                option,
                                color = Color.White,
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )

                            if (showResult && isCorrect) {
                                Icon(
                                    Icons.Default.Check,
                                    "",
                                    tint = Color(0xFF38BDF8)
                                )
                            }

                            if (showResult && isSelected && !isCorrect) {
                                Icon(
                                    Icons.Default.Close,
                                    "",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Button(

                    onClick = {

                        showResult = false

                        if (currentQuestion < quiz.lastIndex) {
                            currentQuestion++
                            selectedAnswer = -1
                        } else {
                            quizFinished = true
                        }

                    },

                    enabled = showResult,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),

                    shape = RoundedCornerShape(18.dp)

                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(buttonGradient),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            "Next Question",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            } else {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        "Quiz Completed 🎉",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Box(contentAlignment = Alignment.Center) {

                        CircularProgressIndicator(
                            progress = score / quiz.size.toFloat(),
                            strokeWidth = 12.dp,
                            modifier = Modifier.size(160.dp),
                            color = Color(0xFF38BDF8)
                        )

                        Text(
                            "$score / ${quiz.size}",
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    Button(
                        onClick = { navController.navigate("home") },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF38BDF8)
                        ),

                        shape = RoundedCornerShape(18.dp)

                    ) {

                        Text(
                            "Return to Dashboard",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}