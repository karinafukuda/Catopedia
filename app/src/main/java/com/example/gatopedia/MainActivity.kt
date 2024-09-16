package com.example.gatopedia

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gatopedia.databinding.ActivityMainBinding
import com.example.gatopedia.presentation.viewmodel.SharedViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val SELECT_ONE_BREED_FIRST_MESSAGE = "Select one breed first."

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]

        val navView: BottomNavigationView = binding.bottomNavigation
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = configureAppBar()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        handleNotSelectedBreedNavigation(navController)
    }

    private fun handleNotSelectedBreedNavigation(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.breedDetailsFragment && sharedViewModel.selectedBreed.value == null) {
                // Se estiver navegando para BreedDetailsFragment e não houver raça selecionada
                Toast.makeText(this, SELECT_ONE_BREED_FIRST_MESSAGE, Toast.LENGTH_SHORT).show()
                navController.navigateUp() // Volta para o fragmento anterior
            }
        }
    }

    private fun configureAppBar(): AppBarConfiguration {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.breedDetailsFragment,
                R.id.favoritesFragment
            )
        )
        return appBarConfiguration
    }
}