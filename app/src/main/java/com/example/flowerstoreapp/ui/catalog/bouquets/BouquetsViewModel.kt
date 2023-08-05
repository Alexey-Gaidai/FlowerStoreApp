package com.example.flowerstoreapp.ui.catalog.bouquets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerstoreapp.domain.FlowerStoreRepository
import com.example.flowerstoreapp.domain.models.Bouquets
import com.example.flowerstoreapp.domain.models.Flower
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class BouquetsViewModel @Inject constructor(private val repository: FlowerStoreRepository) :
    ViewModel() {
    private val _bouquets: MutableLiveData<List<Bouquets>> = MutableLiveData()
    val bouquets: LiveData<List<Bouquets>> get() = _bouquets

    fun loadBouquets(flowerId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (flowerId == 2111) {
                val data = repository.getAllBouquets()
                _bouquets.postValue(data)
            } else {
                val data = repository.getBouquetsByFlower(flowerId)
                _bouquets.postValue(data)
            }

        }
    }
}