package com.example.flowerstoreapp.domain

import com.example.flowerstoreapp.domain.models.Flower

interface FlowerStoreRepository {
    suspend fun getFlowers(): List<Flower>
}