package com.example.flowerstoreapp.data

import android.util.Log
import com.example.flowerstoreapp.data.nw.FlowerApi
import com.example.flowerstoreapp.domain.FlowerStoreRepository
import com.example.flowerstoreapp.domain.models.Bouquets
import com.example.flowerstoreapp.domain.models.Flower
import com.example.flowerstoreapp.domain.models.SingleBouquet
import com.example.flowerstoreapp.domain.models.UserRegistration

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

    override suspend fun getAllBouquets(): List<Bouquets> {
        return try {
            val response = api.getBouquets()
            if (response.isSuccessful) {
                response.body()!!
            } else {
                emptyList()
            }
        } catch (ex: Exception) {
            emptyList()
        }
    }

    override suspend fun getBouquetsByFlower(flowerId: Int): List<Bouquets> {
        return try {
            val response = api.getBouquetByFlower(flowerId)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                emptyList()
            }
        } catch (ex: Exception) {
            emptyList()
        }
    }

    override suspend fun getBouquet(bouquetId: Int): SingleBouquet {
        return try {
            val response = api.getBouquet(bouquetId)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                SingleBouquet(
                    id = -1,
                    name = "",
                    imageUrl = "",
                    description = "",
                    price = 0.0,
                    bouquetComposition = emptyList()
                )
            }
        } catch (ex: Exception) {
            SingleBouquet(
                id = -1,
                name = "",
                imageUrl = "",
                description = "",
                price = 0.0,
                bouquetComposition = emptyList()
            )
        }
    }

    override suspend fun login(username: String, password: String) {
        try{
            val response = api.login(username, password)
            if (response.isSuccessful) {
                Log.d("login", "успешно")
            }
            else {
                Log.d("login", response.code().toString())
            }
        } catch(ex: Exception) {
            Log.d("login", ex.message.toString())
        }
    }

    override suspend fun register(userRegistration: UserRegistration):String {
        return try{
            val response = api.register(userRegistration)
            if (response.code() == 200) {
                Log.d("register", response.body().toString())
                response.body()!!.message
            } else {
                "Произошла ошибка"
            }
        } catch(ex: Exception) {
            Log.d("register", ex.message.toString())
            ex.message.toString()
        }
    }
}