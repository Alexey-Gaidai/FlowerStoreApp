package com.example.flowerstoreapp.ui.login

import android.util.Log
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
class LoginViewModel @Inject constructor(private val repository: FlowerStoreRepository) :
    ViewModel() {
    private val _isUserLoggedIn: MutableLiveData<Event<Unit>> = MutableLiveData()
    val isUserLoggedIn: LiveData<Event<Unit>> get() = _isUserLoggedIn
    private val _isLoginError: MutableLiveData<Event<String>> = MutableLiveData()
    val isLoginError: LiveData<Event<String>> get() = _isLoginError

    fun login(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val isOk = repository.login(login, password)
                Log.d("logincode",isOk.toString())
                if (isOk == 200) {
                    _isUserLoggedIn.postValue(Event(Unit))
                } else {
                    _isLoginError.postValue(Event("Ошибка при вводе логина или пароля"))
                }
            } catch (e: Throwable) {

            }
        }
    }
}