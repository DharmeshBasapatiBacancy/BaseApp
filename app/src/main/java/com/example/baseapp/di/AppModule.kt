package com.example.baseapp.di

import com.example.baseapp.db.dao.UserDao
import com.example.baseapp.network.ApiService
import com.example.baseapp.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideMainRepository(apiService: ApiService, userDao: UserDao): MainRepository {
        return MainRepository(apiService, userDao)
    }


}