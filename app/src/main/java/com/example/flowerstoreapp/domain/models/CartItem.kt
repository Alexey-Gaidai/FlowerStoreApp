package com.example.flowerstoreapp.domain.models

data class CartItem(
    val product: Bouquets,
    var quantity: Int,
)

fun MutableList<CartItem>.cartItemToCreateOrderBouquets(): List<CreateOrderBouquets> {
    return map(){CreateOrderBouquets(
        bouquetID = it.product.id,
        quantity = it.quantity
    )}
}