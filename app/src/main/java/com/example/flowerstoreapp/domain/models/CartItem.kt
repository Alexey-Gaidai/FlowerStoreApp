package com.example.flowerstoreapp.domain.models

data class CartItem(
    val product: SingleBouquet,
    var quantity: Int,
)