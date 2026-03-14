package com.example.healthguardianai.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ScanScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121826))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            ScanTopBar(navController)

            Spacer(modifier = Modifier.height(30.dp))

            ScanFrame()

            Spacer(modifier = Modifier.height(20.dp))

            ScanInstruction()

            Spacer(modifier = Modifier.weight(1f))

            CaptureButton(navController)

            Spacer(modifier = Modifier.height(30.dp))

        }

    }

}

@Composable
fun ScanTopBar(navController: NavController) {

    Row(
        modifier = Modifier.fillMaxWidth(),
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

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            Icons.Default.FlashOn,
            contentDescription = null,
            tint = Color.White
        )

    }
}

@Composable
fun ScanFrame() {

    val infiniteTransition = rememberInfiniteTransition()

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            tween(1500),
            RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .size(260.dp)
                .border(
                    width = 3.dp,
                    brush = Brush.linearGradient(
                        listOf(
                            Color(0xFF6366F1),
                            Color(0xFF4ADE80)
                        )
                    ),
                    shape = RoundedCornerShape(30.dp)
                )
        )

        Box(
            modifier = Modifier
                .size(260.dp)
                .alpha(glowAlpha)
                .border(
                    width = 6.dp,
                    color = Color(0xFF4ADE80),
                    shape = RoundedCornerShape(30.dp)
                )
        )

    }
}

@Composable
fun ScanInstruction() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            "AI Skin Scanner",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Align the skin area inside the frame",
            color = Color.Gray
        )

    }

}

@Composable
fun CaptureButton(navController: NavController) {

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        Button(
            onClick = {

                // SAFE navigation
                navController.navigate("result")

            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(80.dp)
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
                    Icons.Default.CameraAlt,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )

            }

        }

    }

}