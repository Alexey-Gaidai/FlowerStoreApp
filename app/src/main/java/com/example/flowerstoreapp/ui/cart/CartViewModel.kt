package com.example.flowerstoreapp.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerstoreapp.domain.models.CartItem
import com.example.flowerstoreapp.utils.ShoppingCart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private val _cart = MutableStateFlow<List<CartItem>>(emptyList())
    val cart: Flow<List<CartItem>> = _cart

    init {
        observeCartChanges()
    }

    private fun observeCartChanges() {
        viewModelScope.launch {
            ShoppingCart.cartFlow.collect { cartItems ->
                _cart.value = cartItems
            }
        }
    }
}