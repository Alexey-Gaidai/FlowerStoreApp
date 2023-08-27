package com.example.flowerstoreapp.domain.models

import com.google.gson.annotations.SerializedName

data class BouquetsInOrder(
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double
)
