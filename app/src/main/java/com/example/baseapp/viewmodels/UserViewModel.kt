package com.example.baseapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.baseapp.repository.MainRepository
import com.example.baseapp.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {


    fun getThatJoke() = liveData(Dispatchers.IO) {
        emit(ApiResponse.Loading(null))
        try {
            val apiResponse = mainRepository.getThatJoke()
            emit(ApiResponse.Success(apiResponse))

        } catch (exception: Exception) {
            emit(exception.localizedMessage?.let { ApiResponse.Error(null, it) })
        }
    }

}