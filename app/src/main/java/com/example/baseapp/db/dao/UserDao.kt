package com.example.baseapp.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.baseapp.db.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getUsers(): List<User>

}