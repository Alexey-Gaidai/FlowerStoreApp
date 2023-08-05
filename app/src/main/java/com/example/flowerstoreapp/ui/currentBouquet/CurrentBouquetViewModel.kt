package com.example.flowerstoreapp.ui.currentBouquet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowerstoreapp.domain.FlowerStoreRepository
import com.example.flowerstoreapp.domain.models.SingleBouquet
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class CurrentBouquetViewModel @Inject constructor(private val repository: FlowerStoreRepository): ViewModel() {
    private val _bouquet: MutableLiveData<SingleBouquet> = MutableLiveData()
    val bouquet: LiveData<SingleBouquet> get() = _bouquet

    fun loadBouquet(bouquetId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getBouquet(bouquetId)
            _bouquet.postValue(data)
        }
    }
}