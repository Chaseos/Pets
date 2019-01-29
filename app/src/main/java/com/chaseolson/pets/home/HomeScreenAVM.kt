package com.chaseolson.pets.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.chaseolson.pets.home.model.PetListItemViewModel

class HomeScreenAVM : ViewModel() {
    private val presentError = MutableLiveData<String>()
    private val config = PagedList.Config.Builder().setPageSize(20).build()
    private val petFeedFactory: PetDataSourceFactory
    private val pets: LiveData<PagedList<PetListItemViewModel.Pet>>

    init {
        val listener = object : PetFeed.Listener {
            override fun presentError(error: String) {
                presentError.postValue(error)
            }
        }

        petFeedFactory = PetDataSourceFactory(listener)
        pets = LivePagedListBuilder(petFeedFactory, config).build()
    }

    /**
     * Observables
     */
    fun presentError(): LiveData<String> = presentError
    fun pets(): LiveData<PagedList<PetListItemViewModel.Pet>> = pets
}