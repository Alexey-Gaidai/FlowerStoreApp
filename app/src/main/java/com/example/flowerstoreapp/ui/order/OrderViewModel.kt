package com.example.flowerstoreapp.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerstoreapp.App
import com.example.flowerstoreapp.domain.FlowerStoreRepository
import com.example.flowerstoreapp.domain.models.CreateOrder
import com.example.flowerstoreapp.domain.models.UserRegistration
import com.example.flowerstoreapp.domain.models.cartItemToCreateOrderBouquets
import com.example.flowerstoreapp.ui.generic.Event
import com.example.flowerstoreapp.utils.ShoppingCart
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class OrderViewModel @Inject constructor(private val repository: FlowerStoreRepository) :
    ViewModel() {
    private val _isOk: MutableLiveData<Event<Unit>> = MutableLiveData()
    val isOk: LiveData<Event<Unit>> get() = _isOk

    fun createOrder(city: String, street: String, house: String, apartments: String) {
        val userId = App.userManager.getUserId().toInt()
        val order = CreateOrder(
            userId,
            ShoppingCart.getCart().cartItemToCreateOrderBouquets(),
            city,
            street,
            house,
            apartments
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.createOrder(order)
                if (response == 200) {
                    _isOk.postValue(Event(Unit))
                    ShoppingCart.clearCart()
                }
            } catch (e: Throwable) {

            }
        }
    }
}