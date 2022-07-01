package com.example.baseapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baseapp.R
import com.example.baseapp.databinding.ActivityMainBinding
import com.example.baseapp.utils.NetworkUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}