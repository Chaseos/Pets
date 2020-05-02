package com.chaseolson.pets.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chaseolson.pets.R
import com.chaseolson.pets.core.LiveEvent
import com.chaseolson.pets.core.LiveEventData
import com.chaseolson.pets.home.models.PetListViewState
import com.chaseolson.pets.network.endpoints.PetFinderEndpoints
import com.chaseolson.pets.search.SearchModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeScreenViewModel(repo: PetFinderEndpoints) : ViewModel() {
    val isLoading = MutableLiveData(true)
    val scrollToTop = LiveEvent()
    val petClickedAction = LiveEvent()
    private val petFeedFactory = object : DataSource.Factory<Int, PetListViewState.Pet>() {
        override fun create(): DataSource<Int, PetListViewState.Pet> =
            AnimalFeed(repo, SearchModel(), this@HomeScreenViewModel)
    }
    var animalsLiveData = LivePagedListBuilder(petFeedFactory, 20).build()
    val swipeRefreshListener = MutableLiveData(SwipeRefreshLayout.OnRefreshListener {
        animalsLiveData.value?.dataSource?.invalidate()
    })

    val bottomNavListener = MutableLiveData(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> if (item.isChecked) scrollToTop()
                else -> item.isChecked = true
            }
            true
        })

    fun scrollToTop() = scrollToTop.callEvent()
    fun petClicked() = petClickedAction.callEvent()
}