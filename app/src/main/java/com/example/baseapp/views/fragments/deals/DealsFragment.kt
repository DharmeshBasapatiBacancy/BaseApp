package com.example.baseapp.views.fragments.deals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.baseapp.databinding.FragmentDealsBinding
import com.example.baseapp.utils.ApiResponse
import com.example.baseapp.utils.NetworkUtils
import com.example.baseapp.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DealsFragment : Fragment() {

    private lateinit var binding: FragmentDealsBinding

    private val userViewModel: UserViewModel by viewModels()

    private lateinit var contentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (!::contentView.isInitialized) {
            binding = FragmentDealsBinding.inflate(inflater, container, false)
            contentView = binding.root

        }

        return contentView
    }

    companion object {
    }
}