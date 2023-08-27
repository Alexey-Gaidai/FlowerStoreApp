package com.example.flowerstoreapp.domain.models


import com.google.gson.annotations.SerializedName

data class Orders(
    @SerializedName("orderId")
    val id: Int,
    @SerializedName("address")
    val address: String,
    @SerializedName("bouquets")
    val bouquets: List<BouquetsInOrder>,
    @SerializedName("orderDate")
    val orderDate: String
)