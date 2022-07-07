package com.example.baseapp.views.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.baseapp.R
import com.example.baseapp.databinding.FragmentHomeBinding
import com.example.baseapp.databinding.FragmentHomeDetailBinding
import com.example.baseapp.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDetailFragment : Fragment() {

    private lateinit var binding: FragmentHomeDetailBinding
    private lateinit var contentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (!::contentView.isInitialized) {
            binding = FragmentHomeDetailBinding.inflate(inflater, container, false)
            contentView = binding.root
            binding.apply {
                labelTextView.setOnClickListener {
                    findNavController().popBackStack()
                }
            }



        }

        return contentView
    }




}