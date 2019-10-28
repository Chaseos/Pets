package com.chaseolson.pets.newhome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaseolson.pets.repo.MobileEndpointsNew
import kotlinx.coroutines.launch

class HomeScreenViewModel2(val repo: MobileEndpointsNew) : ViewModel() {

    private val isLoading = MutableLiveData(true)

    init {
        viewModelScope.launch {
            runCatching {
                val petsListingResponse = repo.getPetListingNew()
                if (petsListingResponse.isSuccessful) {
                    val data = petsListingResponse.body()?.pet
                }
            }
        }
    }
}