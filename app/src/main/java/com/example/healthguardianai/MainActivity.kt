package com.example.healthguardianai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.healthguardianai.navigation.NavGraph
import com.example.healthguardianai.ui.theme.HealthGuardianAITheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            HealthGuardianAITheme {

                val navController = rememberNavController()

                NavGraph(navController)

            }

        }
    }
}