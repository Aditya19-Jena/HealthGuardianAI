package com.example.healthguardianai.screens

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.healthguardianai.network.RetrofitClient
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetectionScreen(
    navController: NavController,
    imagePath: String
) {

    val coroutineScope = rememberCoroutineScope()

    var loading by remember { mutableStateOf(true) }
    var disease by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {

        coroutineScope.launch {

            try {

                // Check if image path is valid
                if (imagePath.isEmpty()) {

                    disease = "Error"
                    description = "Image path missing"
                    loading = false
                    return@launch
                }

                val file = File(imagePath)

                if (!file.exists()) {

                    disease = "Error"
                    description = "Image file not found"
                    loading = false
                    return@launch
                }

                // Convert image to multipart
                val requestFile =
                    file.asRequestBody("image/*".toMediaType())

                val body =
                    MultipartBody.Part.createFormData(
                        "file",
                        file.name,
                        requestFile
                    )

                // Call backend API
                val response =
                    RetrofitClient.api.uploadImage(body)

                disease = response.disease
                description = response.description

            } catch (e: Exception) {

                // Network failure or backend not running
                disease = "Connection Error"
                description =
                    "Unable to connect to AI server. Please start backend."

            }

            loading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Detection") }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {

            if (loading) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    CircularProgressIndicator()

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("Analyzing image with AI...")

                }

            } else {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Detection Result",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Disease: $disease",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(description)

                    Spacer(modifier = Modifier.height(30.dp))

                    // Navigate to result screen
                    Button(
                        onClick = {

                            navController.navigate(
                                "result/$disease/${Uri.encode(description)}"
                            )

                        }
                    ) {
                        Text("View Full Result")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedButton(
                        onClick = {

                            navController.popBackStack()

                        }
                    ) {

                        Text("Back")

                    }
                }
            }
        }
    }
}