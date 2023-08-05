package com.example.flowerstoreapp.domain.models


import com.google.gson.annotations.SerializedName

data class SingleBouquet(
    @SerializedName("bouquetComposition")
    val bouquetComposition: List<BouquetComposition>,
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
) {
    data class BouquetComposition(
        @SerializedName("flowerID")
        val flowerID: Int,
        @SerializedName("flowerName")
        val flowerName: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("quantity")
        val quantity: Int
    )
}