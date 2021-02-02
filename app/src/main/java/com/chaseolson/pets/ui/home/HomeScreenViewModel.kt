package com.chaseolson.pets.ui.home

import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chaseolson.pets.R
import com.chaseolson.pets.core.LiveEvent
import com.chaseolson.pets.core.LiveEventData
import com.chaseolson.pets.ui.details.PetDetailViewState
import com.chaseolson.pets.network.PetFinderResponse
import com.chaseolson.pets.network.endpoints.PetFinderEndpoints
import com.chaseolson.pets.network.responseToDetailViewState
import com.chaseolson.pets.ui.search.SearchModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeScreenViewModel(repo: PetFinderEndpoints) : ViewModel() {
    val isLoading = MutableLiveData(true)
    val petResponse: MutableList<PetFinderResponse.Animal> = mutableListOf()
    val scrollToTop = LiveEvent()
    val searchClicked = LiveEvent()
    val petClickedAction = LiveEventData<PetDetailDto>()

    private val petFeedFactory = object : DataSource.Factory<Int, PetListViewState.Pet>() {
        override fun create(): DataSource<Int, PetListViewState.Pet> =
            PetsHomeFeed(repo, SearchModel(), this@HomeScreenViewModel)
    }
    var petsLiveData = LivePagedListBuilder(petFeedFactory, 20).build()
    val swipeRefreshListener = MutableLiveData(SwipeRefreshLayout.OnRefreshListener {
        petsLiveData.value?.dataSource?.invalidate()
    })

    val bottomNavListener =
        MutableLiveData(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> if (item.isChecked) scrollToTop()
                else -> item.isChecked = true
            }
            true
        })

    fun scrollToTop() = scrollToTop.callEvent()

    fun searchClicked() = searchClicked.callEvent()

    fun petClicked(petId: Int, petImage: ImageView, petName: TextView) =
        petClickedAction.throttleEvent(
            PetDetailDto(
                petDetailViewState = petResponse.first { petId == it.id }.responseToDetailViewState(),
                petImage = petImage,
                petName = petName
            )
        )
}

data class PetDetailDto(
    val petDetailViewState: PetDetailViewState?,
    val petImage: ImageView,
    val petName: TextView
)