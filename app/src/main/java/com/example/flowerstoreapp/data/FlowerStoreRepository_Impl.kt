package com.example.flowerstoreapp.data

import com.example.flowerstoreapp.data.nw.FlowerApi
import com.example.flowerstoreapp.domain.FlowerStoreRepository
import com.example.flowerstoreapp.domain.models.Flower

class FlowerStoreRepository_Impl(private val api: FlowerApi) : FlowerStoreRepository {
    override suspend fun getFlowers(): List<Flower> {
        return try {
            val response = api.getFlowers()
            if (response.isSuccessful) {
                response.body()!!
            } else {
                emptyList()
            }
        } catch (ex: Exception) {
            emptyList()
        }
    }
}