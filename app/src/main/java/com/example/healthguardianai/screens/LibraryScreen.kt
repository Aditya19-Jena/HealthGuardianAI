package com.example.healthguardianai.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class LibraryItem(
    val title: String,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(navController: NavController) {

    val libraryItems = listOf(
        LibraryItem(
            "Skin Health",
            "Learn about common skin conditions, prevention, and treatments."
        ),
        LibraryItem(
            "Allergies",
            "Understand allergic reactions and how to manage them."
        ),
        LibraryItem(
            "Infections",
            "Information about fungal, bacterial, and viral infections."
        ),
        LibraryItem(
            "Healthy Lifestyle",
            "Tips for maintaining a healthy lifestyle and preventing diseases."
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Health Library") }
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

            items(libraryItems) { item ->

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
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = "Library"
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {

                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = item.description,
                                style = MaterialTheme.typography.bodyMedium
                            )

                        }

                    }

                }

            }

        }

    }
}