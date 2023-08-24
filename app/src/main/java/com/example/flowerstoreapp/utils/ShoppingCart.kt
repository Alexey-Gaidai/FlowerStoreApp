package com.example.flowerstoreapp.utils

import android.content.Context
import android.widget.Toast
import com.example.flowerstoreapp.domain.models.CartItem
import io.paperdb.Paper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ShoppingCart {

    companion object {
        private val _cartFlow: MutableStateFlow<MutableList<CartItem>> = MutableStateFlow(getCart())

        val cartFlow: Flow<MutableList<CartItem>>
            get() = _cartFlow

        fun addItem(cartItem: CartItem, context: Context) {
            val cart = ShoppingCart.getCart()

            val targetItem = cart.singleOrNull { it.product.id == cartItem.product.id }
            if (targetItem == null) {
                cartItem.quantity++
                cart.add(cartItem)
            } else {
                targetItem.quantity++
            }
            ShoppingCart.saveCart(cart)
            _cartFlow.value = cart
            Toast.makeText(context, "Товар добавлен в корзину", Toast.LENGTH_SHORT).show()
        }

        fun removeItem(cartItem: CartItem, context: Context) {
            val cart = ShoppingCart.getCart()

            val targetItem = cart.singleOrNull { it.product.id == cartItem.product.id }
            if (targetItem != null) {
                if (targetItem.quantity > 1) {
                    targetItem.quantity--
                } else {
                    cart.remove(targetItem)
                }
                ShoppingCart.saveCart(cart)
                _cartFlow.value = cart
            }
            Toast.makeText(context, "Товар удален из корзины", Toast.LENGTH_SHORT).show()
        }

        fun saveCart(cart: MutableList<CartItem>) {
            Paper.book().write("cart", cart)
        }

        fun getCart(): MutableList<CartItem> {
            return Paper.book().read("cart", mutableListOf())!!
        }

        fun getShoppingCartSize(): Int {
            var cartSize = 0
            ShoppingCart.getCart().forEach {
                cartSize += it.quantity;
            }

            return cartSize
        }
    }

}
