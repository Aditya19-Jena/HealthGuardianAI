package com.example.healthguardianai.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.healthguardianai.screens.*

@Composable
fun MainNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(navController)
        }

        composable("scan") {
            ScanScreen(navController)
        }

        composable("categories") {
            CategoriesScreen(navController)
        }

        composable("quiz") {
            QuizScreen(navController)
        }

        composable("profile") {
            ProfileScreen(navController)
        }

        composable(
            route = "deepconcept/{title}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val title =
                backStackEntry.arguments?.getString("title") ?: ""

            DeepConceptScreen(
                title,
                navController = TODO(),
                topic = TODO(),
            )

        }

        composable("ai_chat") {
            AIChatScreen()
        }

        composable("symptom_checker") {
            SymptomCheckerScreen()
        }

        composable("report_explainer") {
            MedicalReportScreen()
        }
    }
}