package com.example.baseapp.repository

import com.example.baseapp.db.dao.UserDao
import com.example.baseapp.network.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
) {

    suspend fun getThatJoke() = apiService.getThatJoke()


}