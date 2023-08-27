package com.example.flowerstoreapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerstoreapp.domain.FlowerStoreRepository
import com.example.flowerstoreapp.domain.models.Orders
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: FlowerStoreRepository) :
    ViewModel() {
    private val _orders: MutableLiveData<List<Orders>> = MutableLiveData()
    val orders: LiveData<List<Orders>> get() = _orders

    init {
        loadOrders()
    }

    private fun loadOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getUserOrders()
            _orders.postValue(result)
        }
    }
}