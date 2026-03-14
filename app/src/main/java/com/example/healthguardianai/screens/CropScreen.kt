package com.example.healthguardianai.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropScreen(
    navController: NavController,
    imagePath: String
) {

    val imageUri =
        if (imagePath.isNotEmpty())
            Uri.fromFile(File(imagePath))
        else null

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crop Image") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Preview Image",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (imageUri != null) {

                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

            } else {

                Text("No image selected")
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {

                    if (imagePath.isNotEmpty()) {

                        navController.navigate(
                            "detection/${Uri.encode(imagePath)}"
                        )

                    }

                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Analyze with AI")

            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Retake Image")

            }
        }
    }
}