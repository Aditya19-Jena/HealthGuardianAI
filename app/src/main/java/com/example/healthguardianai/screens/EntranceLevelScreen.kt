package com.example.healthguardianai.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EntranceLevelScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Entrance Level Explanation",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Electric Motor")

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "A device that converts electrical energy into mechanical energy using electromagnetic induction."
        )
    }
}
