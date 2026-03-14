package com.example.healthguardianai.screens

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, "home"),
        BottomNavItem("Scan", Icons.Default.CameraAlt, "scan"),
        BottomNavItem("Categories", Icons.Default.List, "categories"),
        BottomNavItem("Quiz", Icons.Default.Quiz, "quiz"),
        BottomNavItem("Profile", Icons.Default.Person, "profile")
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(

        modifier = Modifier
            .height(75.dp)
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp)
            ),

        containerColor = Color(0xFF111827),
        tonalElevation = 0.dp

    ) {

        items.forEach { item ->

            val selected = currentRoute == item.route

            NavigationBarItem(

                selected = selected,

                onClick = {

                    navController.navigate(item.route) {

                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = if (selected)
                            Color(0xFF38BDF8)
                        else
                            Color.LightGray
                    )
                },

                label = {
                    Text(
                        text = item.label,
                        color = if (selected)
                            Color(0xFF38BDF8)
                        else
                            Color.LightGray
                    )
                },

                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0xFF1E293B)
                )
            )
        }
    }
}