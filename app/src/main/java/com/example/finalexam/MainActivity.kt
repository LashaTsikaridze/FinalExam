package com.example.finalexam

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.finalexam.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Edge-to-edge display padding handling
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Get NavHostFragment and NavController reliably
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
                ?: throw IllegalStateException("NavHostFragment not found")
        navController = navHostFragment.navController

        // Setup BottomNavigationView with NavController
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setBackgroundColor(Color.TRANSPARENT)

        // Show or hide BottomNavigationView depending on destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragmentSing, R.id.fragmentCreateAcc, R.id.fragmentForgetPassword -> {
                    setBottomNavigationVisibility(View.GONE)
                }
                else -> {
                    setBottomNavigationVisibility(View.VISIBLE)
                }
            }
        }

        // Navigate depending on auth state
        if (auth.currentUser != null) {
            goToMainScreen()
        } else {
            goToSignInScreen()
        }
    }

    private fun goToMainScreen() {
        navController.navigate(R.id.action_fragmentSing_to_fragmentHome)
    }

    private fun goToSignInScreen() {
        setBottomNavigationVisibility(View.GONE)
        navController.navigate(R.id.fragmentSing)
    }

    private fun setBottomNavigationVisibility(visibility: Int) {
        binding.bottomNavigation.visibility = visibility
    }
}
