package com.example.healthguardianai.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ResultScreen(navController: NavController, disease: String, description: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121826))
            .padding(20.dp)
    ) {

        ResultTopBar(navController)

        Spacer(modifier = Modifier.height(20.dp))

        ResultCard()

        Spacer(modifier = Modifier.height(30.dp))

        RecommendationCard()

        Spacer(modifier = Modifier.height(30.dp))

        ActionButtons()

    }

}

@Composable
fun ResultTopBar(navController: NavController) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = { navController.popBackStack() }
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            "AI Analysis Result",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }

}

@Composable
fun ResultCard() {

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.05f)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0xFF6366F1),
                                Color(0xFF4ADE80)
                            )
                        ),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Possible Condition",
                color = Color.Gray
            )

            Text(
                "Dermatitis",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Confidence: 87%",
                color = Color(0xFF4ADE80)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                "Severity: Mild",
                color = Color.White
            )

        }

    }

}

@Composable
fun RecommendationCard() {

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.05f)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                "Recommended Actions",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            RecommendationItem("Use moisturizer regularly")
            RecommendationItem("Avoid potential allergens")
            RecommendationItem("Consult dermatologist if symptoms persist")

        }

    }

}

@Composable
fun RecommendationItem(text: String) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 6.dp)
    ) {

        Box(
            modifier = Modifier
                .size(8.dp)
                .background(Color(0xFF4ADE80), CircleShape)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text,
            color = Color.Gray
        )

    }

}

@Composable
fun ActionButtons() {

    Column {

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6366F1)
            )
        ) {

            Icon(
                Icons.Default.SmartToy,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Ask AI Doctor")

        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50)
        ) {

            Icon(
                Icons.Default.LocalHospital,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Find Nearby Hospital")

        }

    }

}