package com.chaseolson.pets.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import com.chaseolson.pets.R
import com.chaseolson.pets.home.models.PetListItemViewState
import com.chaseolson.pets.network.repo.PetFinderEndpoints
import com.chaseolson.pets.search.SearchModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.chaseolson.pets.core.LiveEvent

class HomeScreenViewModel(repo: PetFinderEndpoints) : ViewModel() {
    val isLoading = MutableLiveData(true)
    val scrollToTop = LiveEvent()
    private val petFeedFactory = object : DataSource.Factory<Int, PetListItemViewState.Pet>() {
        override fun create(): DataSource<Int, PetListItemViewState.Pet> =
            AnimalFeed(repo, SearchModel(), this@HomeScreenViewModel, viewModelScope)
    }
    var animalsLiveData = LivePagedListBuilder(petFeedFactory, 20).build()
//    val swipeRefreshListener = MutableLiveData(SwipeRefreshLayout.OnRefreshListener { animalsLiveData.value?.dataSource?.invalidate() }) FIXME Only clearing the list, not refreshing

    val bottomNavListener =
        MutableLiveData(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> if (item.isChecked) scrollToTop()
                else -> item.isChecked = true
            }
            true
        })

    fun scrollToTop() = scrollToTop.callEvent()
}