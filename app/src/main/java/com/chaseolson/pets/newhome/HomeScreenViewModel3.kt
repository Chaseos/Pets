package com.chaseolson.pets.newhome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import com.chaseolson.pets.home.model.SearchModel
import com.chaseolson.pets.home.presenter.PetDataSourceFactoryNew
import com.chaseolson.pets.repo.MobileEndpointsNew

class HomeScreenViewModel3(val repo: MobileEndpointsNew) : ViewModel() {
    val isLoading = MutableLiveData(true)
    private val presentError = MutableLiveData<String>()
    val petFeedFactory = PetDataSourceFactoryNew(repo, SearchModel(), this, viewModelScope)
    var animalsLiveData = LivePagedListBuilder(petFeedFactory, 20).build()
}