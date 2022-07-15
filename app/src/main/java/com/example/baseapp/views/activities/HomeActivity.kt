package com.example.baseapp.views.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.baseapp.databinding.ActivityHomeBinding
import com.example.baseapp.prefdatastore.DataStoreManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityHomeBinding
    lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            navController =
                (supportFragmentManager.findFragmentById(navHostFragment.id)
                        as NavHostFragment).findNavController()

            bottomNavigationView.setupWithNavController(navController)
        }

        initView()

    }

    private fun initView() {
        dataStoreManager = DataStoreManager(this@HomeActivity)
        GlobalScope.launch {
            dataStoreManager.saveToDataStore("abc@gmail.com","123PQ")
        }

        getCreds()
    }

    private fun getCreds(){
        dataStoreManager.userEmailFlow.asLiveData().observe(this@HomeActivity) {
            var email = it
            Log.d("Email", it)
        }

        dataStoreManager.userIdFlow.asLiveData().observe(this@HomeActivity) {
            var userId = it
            Log.d("userId", it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}