package com.chaseolson.pets.newhome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.chaseolson.pets.home.model.NewPetFinderResponse
import com.chaseolson.pets.home.model.NewPetListItemViewState
import com.chaseolson.pets.home.model.PetListItemViewState
import com.chaseolson.pets.home.model.SearchModel
import com.chaseolson.pets.home.presenter.PetDataSourceFactoryNew
import com.chaseolson.pets.repo.MobileEndpointsNew

class HomeScreenViewModel3(val repo: MobileEndpointsNew) : ViewModel() {
    val isLoading = MutableLiveData(true)
    private val presentError = MutableLiveData<String>()
    var animalsLiveData: LiveData<PagedList<NewPetListItemViewState.NewPet>>

    init {
        val config = PagedList.Config.Builder().build()
        val petFeedFactory = PetDataSourceFactoryNew(repo, SearchModel(), viewModelScope)
        animalsLiveData = LivePagedListBuilder(petFeedFactory, config).build()
    }
}