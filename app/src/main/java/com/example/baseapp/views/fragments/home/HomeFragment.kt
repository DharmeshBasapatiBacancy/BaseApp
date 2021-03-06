package com.example.baseapp.views.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.baseapp.databinding.FragmentHomeBinding
import com.example.baseapp.utils.ApiResponse
import com.example.baseapp.utils.NetworkUtils
import com.example.baseapp.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val userViewModel: UserViewModel by viewModels()

    private lateinit var contentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (!::contentView.isInitialized) {
            binding = FragmentHomeBinding.inflate(inflater, container, false)
            contentView = binding.root
            getThatJoke()
            binding.apply {
                getThatJokeButton.setOnClickListener {
                    if (NetworkUtils.isNetworkConnected(requireContext())) {
                        getThatJoke()
                    } else {
                        binding.thatJokeTextView.text = "No internet connection !!!"
                    }
                }
                goToHomeDetailButton.setOnClickListener {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToHomeDetailFragment())
                }
            }
        }

        return contentView
    }

    private fun getThatJoke() {
        lifecycleScope.launch {
            userViewModel.getThatJoke().observe(requireActivity()) {
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

    companion object {
    }
}