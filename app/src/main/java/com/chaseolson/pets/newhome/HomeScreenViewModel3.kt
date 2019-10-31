package com.chaseolson.pets.newhome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaseolson.pets.core.TokenResponseDto
import com.chaseolson.pets.home.model.NewPetFinderResponse
import com.chaseolson.pets.repo.MobileEndpointsNew
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeScreenViewModel3(val repo: MobileEndpointsNew) : ViewModel() {

    val isLoading = MutableLiveData(true)
    val pets = MutableLiveData<NewPetFinderResponse>()

    init {
        viewModelScope.launch {
            val petsListingResponse = repo.getPetListingNew()
            pets.value = petsListingResponse.body()
        }
    }
}