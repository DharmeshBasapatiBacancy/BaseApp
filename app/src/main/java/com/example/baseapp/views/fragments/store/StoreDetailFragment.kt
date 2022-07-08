package com.example.baseapp.views.fragments.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.baseapp.databinding.FragmentStoreDetailBinding

class StoreDetailFragment : Fragment() {

    private lateinit var binding: FragmentStoreDetailBinding
    private lateinit var contentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (!::contentView.isInitialized) {
            binding = FragmentStoreDetailBinding.inflate(inflater, container, false)
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