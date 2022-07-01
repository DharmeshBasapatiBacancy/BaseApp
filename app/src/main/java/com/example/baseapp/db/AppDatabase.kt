package com.example.baseapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baseapp.db.dao.UserDao
import com.example.baseapp.db.entities.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

}