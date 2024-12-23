package com.example.rakt_daan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.rakt_daan.ui.theme.Rakt_DaanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Rakt_DaanTheme {
                val navController = rememberNavController()  // Initialize NavController
                AppNavigation(navController)  // Pass NavController to navigation composable
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    // Ensure startDestination is a String (route)
    NavHost(
        navController = navController,
        startDestination = "splashScreen"  // Start with splash screen
    ) {
        // Splash Screen
        composable("splashScreen") {
            SplashScreen(navController)  // Pass navController to SplashScreen
        }

        // Login Screen
        composable("loginScreen") {
            LoginScreen(navController)  // Pass navController to LoginScreen
        }

        // Register Screen
        composable("registerScreen") {
            RegisterScreen(navController)  // Pass navController to RegisterScreen
        }
    }
}
