package com.example.flowerstoreapp.domain.models


import com.google.gson.annotations.SerializedName

data class ProfileInfo(
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("phone")
    val phone: String
)