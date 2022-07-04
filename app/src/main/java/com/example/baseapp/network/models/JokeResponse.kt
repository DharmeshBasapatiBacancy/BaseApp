package com.example.baseapp.network.models

data class JokeResponse(
    val attachments: List<Attachment>,
    val response_type: String,
    val username: String
)