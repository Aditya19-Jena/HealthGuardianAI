package com.example.healthguardianai.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlin.math.sin
import kotlin.random.Random

data class Vital(
    val title: String,
    val value: String,
    val progress: Float
)

@Composable
fun HomeScreen(navController: NavController) {

    var heartRate by remember { mutableStateOf(72) }
    var oxygen by remember { mutableStateOf(98) }
    var temperature by remember { mutableStateOf(36.8f) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            heartRate = Random.nextInt(70, 90)
            oxygen = Random.nextInt(95, 100)
            temperature = Random.nextDouble(36.5, 37.2).toFloat()
        }
    }

    val vitals = listOf(
        Vital("Heart Rate", "$heartRate bpm", heartRate / 100f),
        Vital("Oxygen", "$oxygen%", oxygen / 100f),
        Vital("Temp", "$temperature °C", temperature / 40f)
    )

    Scaffold(
        containerColor = Color(0xFF121826),
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121826))
                .padding(padding)
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

            PremiumHeader()

            Spacer(modifier = Modifier.height(20.dp))

            HealthScoreRing()

            Spacer(modifier = Modifier.height(25.dp))

            AIInsightsCard()

            Spacer(modifier = Modifier.height(30.dp))

            SectionHeader("AI Health Tools")

            Spacer(modifier = Modifier.height(12.dp))

            AIToolsSection(navController)

            Spacer(modifier = Modifier.height(30.dp))

            SectionHeader("Live Health Vitals")

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                items(vitals) {
                    VitalCard(it)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            SectionHeader("Heart ECG Monitor")

            ECGWave()

            Spacer(modifier = Modifier.height(30.dp))

            SectionHeader("Weekly Health Analytics")

            WeeklyChart()

            Spacer(modifier = Modifier.height(30.dp))

            SectionHeader("Medication Reminder")

            MedicationReminder()

            Spacer(modifier = Modifier.height(30.dp))

            SectionHeader("Doctor Appointment")

            AppointmentSection()

            Spacer(modifier = Modifier.height(30.dp))

            SectionHeader("Emergency Contact")

            EmergencyPanel()

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun PremiumHeader() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.weight(1f)) {

            Text("Good Morning", color = Color.Gray)

            Text(
                "Aditya",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Box(
            modifier = Modifier
                .size(42.dp)
                .background(Color(0xFF4ADE80), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, null, tint = Color.Black)
        }
    }
}

@Composable
fun HealthScoreRing() {

    val score = 86f

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        Canvas(modifier = Modifier.size(200.dp)) {

            val stroke = 25f

            drawArc(
                color = Color.DarkGray.copy(alpha = 0.2f),
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(stroke)
            )

            drawArc(
                brush = Brush.sweepGradient(
                    listOf(Color(0xFF6366F1), Color(0xFF4ADE80))
                ),
                startAngle = -90f,
                sweepAngle = 360f * (score / 100f),
                useCenter = false,
                style = Stroke(stroke)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                "$score",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge
            )

            Text("Health Score", color = Color.Gray)
        }
    }
}

@Composable
fun AIInsightsCard() {

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(Icons.Default.Psychology, null, tint = Color(0xFF4ADE80))

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    "AI Health Insight",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                "Your vitals look stable today. Maintain hydration and sleep schedule.",
                color = Color.Gray
            )
        }
    }
}

@Composable
fun AIToolsSection(navController: NavController) {

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {

        ToolCard(Icons.Default.Chat, "AI Health Assistant", "Ask health questions") {
            navController.navigate("ai_chat")
        }

        ToolCard(Icons.Default.MedicalServices, "Symptom Checker", "Analyze symptoms") {
            navController.navigate("symptom_checker")
        }

        ToolCard(Icons.Default.Description, "Medical Report Explainer", "Understand reports") {
            navController.navigate("report_explainer")
        }

        ToolCard(Icons.Default.MenuBook, "Health Knowledge", "Explore health topics") {
            navController.navigate("categories")
        }
    }
}

@Composable
fun ToolCard(icon: ImageVector, title: String, description: String, onClick: () -> Unit) {

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(icon, null, tint = Color(0xFF4ADE80), modifier = Modifier.size(30.dp))

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text(title, color = Color.White, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(4.dp))

                Text(description, color = Color.Gray)
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {

    Text(
        title,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun VitalCard(vital: Vital) {

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
        modifier = Modifier.width(150.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(vital.value, color = Color.White, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(6.dp))

            Text(vital.title, color = Color.Gray)
        }
    }
}

@Composable
fun ECGWave() {

    val infinite = rememberInfiniteTransition()

    val phase by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(2000),
            RepeatMode.Restart
        )
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {

        val mid = size.height / 2

        for (x in 0..size.width.toInt() step 8) {

            val y = mid + sin((x + phase) / 40f) * 25

            drawLine(
                color = Color(0xFF4ADE80),
                start = Offset(x.toFloat(), mid),
                end = Offset(x.toFloat() + 8, y.toFloat()),
                strokeWidth = 4f
            )
        }
    }
}

@Composable
fun WeeklyChart() {

    val values = listOf(60, 70, 75, 80, 85, 88, 90)

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {

        val step = size.width / (values.size - 1)

        for (i in 0 until values.size - 1) {

            val x1 = step * i
            val x2 = step * (i + 1)

            val y1 = size.height - values[i] * 2f
            val y2 = size.height - values[i + 1] * 2f

            drawLine(
                color = Color(0xFF6366F1),
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = 6f
            )
        }
    }
}

@Composable
fun MedicationReminder() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f))
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Text("Vitamin D - 8:00 AM", color = Color.White)

            Text("Skin Cream - 9:00 PM", color = Color.Gray)
        }
    }
}

@Composable
fun AppointmentSection() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f))
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Text("Dermatologist", color = Color.White)

            Text("Tomorrow 4:30 PM", color = Color.Gray)
        }
    }
}

@Composable
fun EmergencyPanel() {

    Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
    ) {

        Icon(Icons.Default.Call, null)

        Spacer(modifier = Modifier.width(8.dp))

        Text("Emergency Call")
    }
}