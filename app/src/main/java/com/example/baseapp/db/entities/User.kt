package com.example.baseapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val userId: Int,
    val userName: String,
    val userPassword: String
)
