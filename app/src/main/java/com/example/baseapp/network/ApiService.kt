package com.example.baseapp.network

import com.example.baseapp.network.models.JokeResponse
import retrofit2.http.GET

interface ApiService {

    @GET("slack")
    suspend fun getThatJoke(): JokeResponse


}