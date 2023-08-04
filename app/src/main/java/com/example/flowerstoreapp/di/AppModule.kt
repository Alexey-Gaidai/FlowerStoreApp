package com.example.flowerstoreapp.di

import com.example.flowerstoreapp.data.FlowerStoreRepository_Impl
import com.example.flowerstoreapp.data.nw.FlowerApi
import com.example.flowerstoreapp.domain.FlowerStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi(): FlowerApi{
        return FlowerApi.createApiService()
    }

    @Provides
    @Singleton
    fun provideRepository(
        api: FlowerApi
    ): FlowerStoreRepository {
        return FlowerStoreRepository_Impl(api)
    }
}