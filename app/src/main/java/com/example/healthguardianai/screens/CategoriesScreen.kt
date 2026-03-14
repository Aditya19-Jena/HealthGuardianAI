package com.example.healthguardianai.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.healthguardianai.model.HealthCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(navController: NavController) {

    val categories = getHealthCategories()

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF020617),
            Color(0xFF0F172A),
            Color(0xFF1E293B)
        )
    )

    Scaffold(
        //topBar = {
        //    TopAppBar(
        //        title = { Text("Health Guardian AI") }
        //    )
        //},
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            HeaderSection()

            Spacer(modifier = Modifier.height(20.dp))

            HealthStatsSection()

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Explore Health Topics",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            categories.forEach { category ->

                CategoryCard(
                    category = category,
                    onClick = {
                        navController.navigate("deepconcept/${category.title}")
                    }
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun HeaderSection() {

    Column {

        Text(
            text = "Welcome to",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF94A3B8)
        )

        Text(
            text = "Health Guardian AI",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Your intelligent assistant for understanding health concepts, improving lifestyle habits, and staying informed.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFFCBD5F5)
        )
    }
}

@Composable
fun HealthStatsSection() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        StatCard("12+", "Health Topics")

        StatCard("AI", "Smart Insights")

        StatCard("24/7", "Guidance")
    }
}

@Composable
fun StatCard(title: String, subtitle: String) {

    Card(
        modifier = Modifier
            .width(110.dp)
            .height(80.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E293B)
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF38BDF8)
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }
    }
}

@Composable
fun CategoryCard(
    category: HealthCategory,
    onClick: () -> Unit
) {

    val cardGradient = Brush.horizontalGradient(
        listOf(
            Color(0xFF1E293B),
            Color(0xFF334155)
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = onClick
    ) {

        Row(
            modifier = Modifier
                .background(cardGradient)
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = category.icon,
                contentDescription = category.title,
                tint = Color(0xFF38BDF8),
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text(
                    text = category.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = category.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFCBD5F5)
                )
            }
        }
    }
}

fun getHealthCategories(): List<HealthCategory> {

    return listOf(

        HealthCategory(
            "Nutrition",
            "Healthy eating habits and balanced diet tips.",
            Icons.Default.Restaurant
        ),

        HealthCategory(
            "Mental Health",
            "Stress management and emotional well-being.",
            Icons.Default.SelfImprovement
        ),

        HealthCategory(
            "Fitness",
            "Exercises and physical activity guidance.",
            Icons.Default.FitnessCenter
        ),

        HealthCategory(
            "Sleep",
            "Improve sleep quality and bedtime routines.",
            Icons.Default.Bedtime
        ),

        HealthCategory(
            "Heart Health",
            "Tips for maintaining a healthy cardiovascular system.",
            Icons.Default.MonitorHeart
        ),

        HealthCategory(
            "Preventive Care",
            "Regular checkups and early disease prevention.",
            Icons.Default.LocalHospital
        ),

        HealthCategory(
            "Hydration",
            "Importance of drinking enough water daily.",
            Icons.Default.WaterDrop
        ),

        HealthCategory(
            "Immunity",
            "Strengthen your immune system naturally.",
            Icons.Default.HealthAndSafety
        ),

        HealthCategory(
            "Weight Management",
            "Strategies for maintaining healthy body weight.",
            Icons.Default.Scale
        ),

        HealthCategory(
            "Digital Wellbeing",
            "Managing screen time and digital stress.",
            Icons.Default.PhoneAndroid
        )
    )
}