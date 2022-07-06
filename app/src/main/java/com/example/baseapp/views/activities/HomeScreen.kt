package com.example.baseapp.views.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.baseapp.R
import com.example.baseapp.databinding.ActivityHomeScreenBinding
import com.example.baseapp.views.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            bottomNavigation.setOnNavigationItemSelectedListener(this@HomeScreen)
            bottomNavigation.selectedItemId = R.id.actionHome
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionHome -> {
                supportFragmentManager.beginTransaction()
                    .replace(binding.frameLayout.id, HomeFragment()).commit()
                return true
            }
            R.id.actionStores -> {
                supportFragmentManager.beginTransaction()
                    .replace(binding.frameLayout.id, HomeFragment()).commit()
                return true
            }
            R.id.actionDeals -> {
                supportFragmentManager.beginTransaction()
                    .replace(binding.frameLayout.id, HomeFragment()).commit()
                return true
            }
            R.id.actionRewards -> {
                supportFragmentManager.beginTransaction()
                    .replace(binding.frameLayout.id, HomeFragment()).commit()
                return true
            }
            R.id.actionMore -> {
                supportFragmentManager.beginTransaction()
                    .replace(binding.frameLayout.id, HomeFragment()).commit()
                return true
            }
        }
        return false
    }
}