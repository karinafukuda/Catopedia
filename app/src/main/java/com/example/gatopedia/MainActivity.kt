package com.example.gatopedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gatopedia.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.bottomNavigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = configureAppBar()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        handleNavigation(navView, navController)
    }

    private fun configureAppBar(): AppBarConfiguration {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeNav,
                R.id.searchNav,
                R.id.favoritesNav
            )
        )
        return appBarConfiguration
    }

    private fun handleNavigation(
        navView: BottomNavigationView,
        navController: NavController
    ) {
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragmentItem -> {
                    navController.navigate(R.id.homeNav)
                    true
                }

                R.id.searchFragmentItem -> {
                    navController.navigate(R.id.searchNav)
                    true
                }

                R.id.favoritesFragmentItem -> {
                    navController.navigate(R.id.favoritesNav)
                    true
                }

                else -> false
            }
        }
    }
}