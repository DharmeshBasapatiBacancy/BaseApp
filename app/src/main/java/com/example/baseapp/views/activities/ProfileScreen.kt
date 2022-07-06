package com.example.baseapp.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baseapp.databinding.ActivityProfileScreenBinding
import com.example.baseapp.utils.ViewUtils.showCustomSnackBar

class ProfileScreen : AppCompatActivity() {
    private lateinit var binding: ActivityProfileScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            updateProfileButton.setOnClickListener {
                it.showCustomSnackBar("This is the custom snackbar !!!", layoutInflater)
            }
        }
    }
}