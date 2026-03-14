package com.example.healthguardianai.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var medicalCondition by remember { mutableStateOf("") }
    var emergencyContact by remember { mutableStateOf("") }

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0F172A),
            Color(0xFF1E3A5F),
            Color(0xFF020617)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {

        Scaffold(
            containerColor = Color.Transparent,

            topBar = {

                TopAppBar(
                    title = {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile",
                                tint = Color.White,
                                modifier = Modifier.size(22.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "Health Profile",
                                color = Color.White,
                                fontSize = 20.sp
                            )
                        }
                    },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        scrolledContainerColor = Color.Transparent
                    )
                )
            },

            bottomBar = {
                BottomNavigationBar(navController)
            }

        ) { padding ->

            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ProfileHeader()

                Spacer(modifier = Modifier.height(30.dp))

                ProfileField("Name", name) { name = it }

                ProfileField("Age", age) { age = it }

                ProfileField("Gender", gender) { gender = it }

                ProfileField(
                    "Existing Medical Conditions",
                    medicalCondition
                ) { medicalCondition = it }

                ProfileField(
                    "Emergency Contact",
                    emergencyContact
                ) { emergencyContact = it }

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),

                    shape = RoundedCornerShape(18.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF38BDF8)
                    )
                ) {

                    Text(
                        text = "Save Profile",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun ProfileHeader() {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(140.dp)
    ) {

        Box(
            modifier = Modifier
                .size(140.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF38BDF8),
                            Color(0xFF6366F1),
                            Color(0xFF22C55E)
                        )
                    ),
                    shape = CircleShape
                )
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(120.dp)
                .background(
                    color = Color(0xFF0F172A),
                    shape = CircleShape
                )
        ) {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color.White,
                modifier = Modifier.size(60.dp)
            )
        }
    }

    Spacer(modifier = Modifier.height(14.dp))

    Text(
        text = "Personal Health Profile",
        color = Color.White,
        fontSize = 20.sp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    Spacer(modifier = Modifier.height(12.dp))

    OutlinedTextField(
        value = value,

        onValueChange = onValueChange,

        label = {
            Text(
                text = label,
                color = Color.White
            )
        },

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(14.dp),

        colors = OutlinedTextFieldDefaults.colors(

            focusedBorderColor = Color(0xFF38BDF8),
            unfocusedBorderColor = Color(0xFF38BDF8),

            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,

            cursorColor = Color(0xFF38BDF8),

            focusedLabelColor = Color(0xFF38BDF8),
            unfocusedLabelColor = Color.White,

            focusedContainerColor = Color(0xFF0F172A),
            unfocusedContainerColor = Color(0xFF0F172A)
        )
    )
}