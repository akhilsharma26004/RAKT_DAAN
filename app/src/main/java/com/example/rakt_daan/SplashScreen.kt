package com.example.rakt_daan

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // Display the splash screen content
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Welcome to Rakt Daan",
            fontSize = 24.sp,
            color = Color.Red,
            textAlign = TextAlign.Center
        )
    }

    // Navigate to LoginScreen after a delay
    LaunchedEffect(Unit) {
        delay(1000) // 1-second delay
        navController.navigate("loginScreen") {
            popUpTo("splashScreen") { inclusive = true }
        }
    }
}
