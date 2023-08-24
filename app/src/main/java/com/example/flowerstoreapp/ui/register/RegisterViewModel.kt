package com.example.flowerstoreapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerstoreapp.domain.FlowerStoreRepository
import com.example.flowerstoreapp.domain.models.UserRegistration
import com.example.flowerstoreapp.ui.generic.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: FlowerStoreRepository): ViewModel() {
    private val _isRegistered: MutableLiveData<Event<String>> = MutableLiveData()
    val isRegistered: LiveData<Event<String>> get() = _isRegistered
    fun register(userRegistration: UserRegistration) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.register(userRegistration)
                _isRegistered.postValue(Event(response))
            } catch (e: Throwable) {

            }
        }
    }
}