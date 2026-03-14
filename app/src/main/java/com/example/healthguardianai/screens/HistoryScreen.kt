package com.example.healthguardianai.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class ScanHistory(
    val condition: String,
    val risk: String,
    val date: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController) {

    val historyList = listOf(
        ScanHistory("Skin Irritation", "Medium Risk", "12 Mar 2026"),
        ScanHistory("Allergic Rash", "Low Risk", "10 Mar 2026"),
        ScanHistory("Fungal Infection", "High Risk", "05 Mar 2026")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Scan History") }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {

            items(historyList) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = "History"
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {

                            Text(
                                text = item.condition,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = item.risk,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Text(
                                text = item.date,
                                style = MaterialTheme.typography.bodySmall
                            )

                        }

                    }

                }

            }

        }

    }
}