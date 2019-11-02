package com.chaseolson.pets.newhome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chaseolson.pets.R
import com.chaseolson.pets.core.call
import com.chaseolson.pets.home.model.SearchModel
import com.chaseolson.pets.home.presenter.PetDataSourceFactoryNew
import com.chaseolson.pets.repo.MobileEndpointsNew
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hadilq.liveevent.LiveEvent

class HomeScreenViewModel3(val repo: MobileEndpointsNew) : ViewModel() {
    val isLoading = MutableLiveData(true)
    val scrollToTop = LiveEvent<Unit>()
    val petFeedFactory = PetDataSourceFactoryNew(repo, SearchModel(), this, viewModelScope)
    var animalsLiveData = LivePagedListBuilder(petFeedFactory, 20).build()
    val swipeRefreshListener = MutableLiveData(SwipeRefreshLayout.OnRefreshListener {
//        animalsLiveData.value?.dataSource?.invalidate() FIXME Only clearing the list, not refreshing
    })

    val bottomNavListener = MutableLiveData(BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_home -> if (item.isChecked) scrollToTop()
            else -> item.isChecked = true
        }
        true
    })

    fun scrollToTop() = scrollToTop.call()
}