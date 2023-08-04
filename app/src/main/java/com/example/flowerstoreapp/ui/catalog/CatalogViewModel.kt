package com.example.flowerstoreapp.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerstoreapp.domain.FlowerStoreRepository
import com.example.flowerstoreapp.domain.models.Flower
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.Flow
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class CatalogViewModel @Inject constructor(private val repository: FlowerStoreRepository) : ViewModel() {

    private val _flowers: MutableLiveData<List<Flower>> = MutableLiveData()
    val flowers: LiveData<List<Flower>> get() = _flowers

    init {
        loadFlowers()
    }

    private fun loadFlowers(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getFlowers()
            _flowers.postValue(data)
        }
    }
}