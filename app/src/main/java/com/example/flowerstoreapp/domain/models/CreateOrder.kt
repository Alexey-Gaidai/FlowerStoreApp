package com.example.flowerstoreapp.domain.models

import com.google.gson.annotations.SerializedName

data class CreateOrder(
    @SerializedName("userID")
    val userID: Int,
    @SerializedName("orderedBouquets")
    val orderedBouquets: List<CreateOrderBouquets>,
    @SerializedName("city")
    val city: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("house")
    val house: String,
    @SerializedName("apartment")
    val apartment: String
)

data class CreateOrderBouquets(
    @SerializedName("bouquetID")
    val bouquetID: Int,
    @SerializedName("quantity")
    val quantity: Int
)
