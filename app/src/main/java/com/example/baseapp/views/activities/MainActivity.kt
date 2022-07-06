package com.example.baseapp.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.baseapp.databinding.ActivityMainBinding
import com.example.baseapp.utils.ApiResponse
import com.example.baseapp.utils.NetworkUtils.isNetworkConnected
import com.example.baseapp.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getThatJoke()
        binding.apply {
            getThatJokeButton.setOnClickListener {
                if (isNetworkConnected(this@MainActivity)) {
                    getThatJoke()
                } else {
                    binding.thatJokeTextView.text = "No internet connection !!!"
                }
            }
        }
    }

    private fun getThatJoke() {
        lifecycleScope.launch {
            userViewModel.getThatJoke().observe(this@MainActivity) {
                when (it) {
                    is ApiResponse.Success -> {
                        if (it.data?.attachments?.isNotEmpty()!!) {
                            binding.thatJokeTextView.text = it.data.attachments[0].text
                        }
                    }
                    is ApiResponse.Error -> {
                        binding.thatJokeTextView.text = "Try again !!!"
                    }
                    is ApiResponse.Loading -> {
                        binding.thatJokeTextView.text = "Loading..."
                    }
                    else -> {
                        binding.thatJokeTextView.text = "Try again !!!"
                    }
                }
            }
        }
    }
}