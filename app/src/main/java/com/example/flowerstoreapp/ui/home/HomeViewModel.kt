package com.example.flowerstoreapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerstoreapp.domain.FlowerStoreRepository
import com.example.flowerstoreapp.domain.models.Bouquets
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: FlowerStoreRepository) :
    ViewModel() {
    private val _topSelling: MutableLiveData<List<Bouquets>> = MutableLiveData()
    val topSelling: LiveData<List<Bouquets>> get() = _topSelling
    private val _topSellingWithRoses: MutableLiveData<List<Bouquets>> = MutableLiveData()
    val topSellingWithRoses: LiveData<List<Bouquets>> get() = _topSellingWithRoses

    init {
        loadBouquets()
    }
    private fun loadBouquets() {
        viewModelScope.launch(Dispatchers.IO) {
            val top = repository.getTopSelling()
            _topSelling.postValue(top)
            val topWithRoses = repository.getTopSellingWithRoses()
            _topSellingWithRoses.postValue(topWithRoses)
        }
    }
}