package com.example.baseapp.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.baseapp.R
import com.example.baseapp.databinding.ActivityHomeScreenWithNavGraphBinding
import com.example.baseapp.utils.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenWithNavGraphActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityHomeScreenWithNavGraphBinding
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityHomeScreenWithNavGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.findNavController()

        val navGraphIds = listOf(
            R.navigation.home_nav_graph,
            R.navigation.store_nav_graph,
            R.navigation.deals_nav_graph,
            R.navigation.rewards_nav_graph,
            R.navigation.more_nav_graph
        )

        val controller = binding.bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = binding.navHostFragment.id,
            intent = intent
        )

        currentNavController = controller

    }

    override fun onSupportNavigateUp(): Boolean {
       return currentNavController?.value?.navigateUp() ?: false
    }
}