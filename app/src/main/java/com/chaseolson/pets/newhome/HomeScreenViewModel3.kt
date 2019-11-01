package com.chaseolson.pets.newhome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.chaseolson.pets.home.model.NewPetFinderResponse
import com.chaseolson.pets.home.model.PetListItemViewState
import com.chaseolson.pets.home.model.SearchModel
import com.chaseolson.pets.home.presenter.PetDataSourceFactoryNew
import com.chaseolson.pets.home.presenter.PetFeedNew
import com.chaseolson.pets.repo.MobileEndpointsNew
import kotlinx.coroutines.launch

class HomeScreenViewModel3(val repo: MobileEndpointsNew) : ViewModel() {

    val isLoading = MutableLiveData(true)
    val petsResponse = MutableLiveData<NewPetFinderResponse>()
    private val presentError = MutableLiveData<String>()
    private val config = PagedList.Config.Builder().build()
    private val petFeedFactory = PetDataSourceFactoryNew(repo, SearchModel(), viewModelScope)
    var animalsLiveData = LivePagedListBuilder(petFeedFactory, config).build()

    init {
        viewModelScope.launch {
            val petsListingResponse = repo.getPetListingNew()
            petsResponse.value = petsListingResponse.body()
        }
    }
}