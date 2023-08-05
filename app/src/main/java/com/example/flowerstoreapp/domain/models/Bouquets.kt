package com.example.flowerstoreapp.domain.models


import com.google.gson.annotations.SerializedName

data class Bouquets(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double
)
