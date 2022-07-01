package com.example.baseapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.baseapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {


}