package com.example.healthguardianai.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.healthguardianai.screens.*

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH   // FIXED
    ) {

        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }

        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        composable(Routes.HOME) {
            HomeScreen(navController)
        }

        composable(Routes.SCAN) {
            ScanScreen(navController)
        }

        composable("${Routes.CROP}/{imagePath}") { backStackEntry ->

            val imagePath =
                backStackEntry.arguments?.getString("imagePath") ?: ""

            CropScreen(
                navController = navController,
                imagePath = imagePath
            )
        }

        composable("${Routes.DETECTION}/{imagePath}") { backStackEntry ->

            val imagePath =
                backStackEntry.arguments?.getString("imagePath") ?: ""

            DetectionScreen(
                navController = navController,
                imagePath = imagePath
            )
        }

        composable(Routes.RESULT) {

            ResultScreen(
                navController = navController,
                disease = "Dermatitis",
                description = "AI detected a possible skin irritation."
            )
        }

        composable("${Routes.ASK_AI}/{disease}") { backStackEntry ->

            val disease =
                backStackEntry.arguments?.getString("disease") ?: ""

            AskQuestionScreen(
                navController = navController,
                disease = disease
            )
        }

        composable(Routes.HISTORY) {
            HistoryScreen(navController)
        }

        composable(Routes.CATEGORIES) {
            CategoriesScreen(navController)
        }

        composable(Routes.QUIZ) {
            QuizScreen(navController)
        }

        composable(Routes.LIBRARY) {
            LibraryScreen(navController)
        }

        composable(Routes.PROFILE) {
            ProfileScreen(navController)
        }

        composable("${Routes.DEEP_CONCEPT}/{topic}") { backStackEntry ->

            val topic =
                backStackEntry.arguments?.getString("topic") ?: ""

            DeepConceptScreen(
                navController = navController,
                topic = topic,
                title = topic
            )
        }

        composable(Routes.AI_CHAT) {
            AIChatScreen()
        }

        composable(Routes.SYMPTOM_CHECKER) {
            SymptomCheckerScreen()
        }

        composable(Routes.REPORT_EXPLAINER) {
            MedicalReportScreen()
        }
    }
}