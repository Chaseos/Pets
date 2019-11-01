package com.chaseolson.pets.newhome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val config = PagedList.Config.Builder().setPageSize(10).build()
    private val petFeedFactory: PetDataSourceFactoryNew
    private val pets: LiveData<PagedList<PetListItemViewState.Pet>>

    init {
        val listener = object : PetFeedNew.Listener {
            override fun presentError(error: String) {
                presentError.postValue(error)
            }
        }

        petFeedFactory = PetDataSourceFactoryNew(listener, repo, SearchModel(), viewModelScope)
        pets = LivePagedListBuilder(petFeedFactory, config).build()

        viewModelScope.launch {
            val petsListingResponse = repo.getPetListingNew()
            petsResponse.value = petsListingResponse.body()
        }
    }

    /**
     * Observables
     */
    fun presentError(): LiveData<String> = presentError
    fun pets(): LiveData<PagedList<PetListItemViewState.Pet>> = pets

    /**
     * Actionables
     */
    fun swipeRefresh() { pets().value?.dataSource?.invalidate() }

    fun searchByZipCode(zipCode: String) {
        petFeedFactory.searchModel = petFeedFactory.searchModel.copy(location = zipCode)
        pets.value?.dataSource?.invalidate()
    }

    fun search(searchModel: SearchModel) {
        petFeedFactory.searchModel = searchModel
        pets.value?.dataSource?.invalidate()
    }
}