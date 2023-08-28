package com.example.flowerstoreapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerstoreapp.domain.FlowerStoreRepository
import com.example.flowerstoreapp.domain.models.Orders
import com.example.flowerstoreapp.domain.models.ProfileInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: FlowerStoreRepository) :
    ViewModel() {
    private val _orders: MutableLiveData<List<Orders>> = MutableLiveData()
    val orders: LiveData<List<Orders>> get() = _orders

    private val _profile: MutableLiveData<ProfileInfo> = MutableLiveData()
    val profile: LiveData<ProfileInfo> get() = _profile

    init {
        loadOrders()
        loadProfileInfo()
    }

    fun loadOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getUserOrders()
            _orders.postValue(result)
        }
    }

    private fun loadProfileInfo(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getProfileInfo()
            _profile.postValue(result)
        }
    }
}