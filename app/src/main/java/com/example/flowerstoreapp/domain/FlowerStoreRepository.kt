package com.example.flowerstoreapp.domain

import com.example.flowerstoreapp.domain.models.Bouquets
import com.example.flowerstoreapp.domain.models.CreateOrder
import com.example.flowerstoreapp.domain.models.Flower
import com.example.flowerstoreapp.domain.models.Orders
import com.example.flowerstoreapp.domain.models.ProfileInfo
import com.example.flowerstoreapp.domain.models.SingleBouquet
import com.example.flowerstoreapp.domain.models.UserRegistration

interface FlowerStoreRepository {
    suspend fun getFlowers(): List<Flower>
    suspend fun getAllBouquets(): List<Bouquets>
    suspend fun getBouquetsByFlower(flowerId: Int): List<Bouquets>
    suspend fun getBouquet(bouquetId: Int): SingleBouquet
    suspend fun login(username: String, password: String): Int
    suspend fun register(userRegistration: UserRegistration): String
    suspend fun createOrder(order: CreateOrder): Int
    suspend fun getUserOrders(): List<Orders>
    suspend fun getProfileInfo(): ProfileInfo
    suspend fun getTopSelling(): List<Bouquets>
    suspend fun getTopSellingWithRoses(): List<Bouquets>
}