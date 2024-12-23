package com.example.rakt_daan
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
class MainscreenActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainscreen)
        // Initialize views and setup navigation
        setupViews()
        setupNavigation()
    }
    private fun setupViews() {
        // Initialize BottomNavigationView
        bottomNav = findViewById(R.id.bottomnav)
    }
    private fun setupNavigation() {
        // Get the NavController from NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        // Link BottomNavigationView with NavController
        NavigationUI.setupWithNavController(bottomNav, navController)
    }
}
