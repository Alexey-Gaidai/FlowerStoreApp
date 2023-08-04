package com.example.flowerstoreapp.domain.models


import com.google.gson.annotations.SerializedName

data class Flower(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)