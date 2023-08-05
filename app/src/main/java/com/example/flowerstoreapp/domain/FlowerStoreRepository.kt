package com.example.flowerstoreapp.domain

import com.example.flowerstoreapp.domain.models.Bouquets
import com.example.flowerstoreapp.domain.models.Flower
import com.example.flowerstoreapp.domain.models.SingleBouquet

interface FlowerStoreRepository {
    suspend fun getFlowers(): List<Flower>
    suspend fun getAllBouquets(): List<Bouquets>
    suspend fun getBouquetsByFlower(flowerId: Int): List<Bouquets>
    suspend fun getBouquet(bouquetId: Int): SingleBouquet
}