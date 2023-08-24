package com.example.flowerstoreapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerstoreapp.domain.FlowerStoreRepository
import com.example.flowerstoreapp.ui.generic.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: FlowerStoreRepository) : ViewModel() {

    fun login(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val isOk = repository.login(login, password)
            } catch (e: Throwable) {

            }
        }
    }
}