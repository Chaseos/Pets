package com.chaseolson.pets.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PageKeyedDataSource
import com.chaseolson.pets.network.responseToViewState
import com.chaseolson.pets.network.endpoints.PetFinderEndpoints
import com.chaseolson.pets.ui.search.SearchModel
import kotlinx.coroutines.launch

class PetsHomeFeed(
    private val api: PetFinderEndpoints,
    private val searchModel: SearchModel,
    private val viewModel: HomeScreenViewModel
) : PageKeyedDataSource<Int, PetListViewState.Pet>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PetListViewState.Pet>
    ) {
        viewModel.viewModelScope.launch {
            val petsResponse = api.getPetListing(
                type = searchModel.animal,
                breed = searchModel.breed,
                size = searchModel.size,
                gender = searchModel.sex,
                age = searchModel.age,
                location = searchModel.location ?: "75001",
                page = 1,
                sort = "distance"
            )
            if (petsResponse.isSuccessful) {
                viewModel.isLoading.postValue(false)
                viewModel.petResponse.addAll(petsResponse.body()?.animals!!)
                val pets = petsResponse.body()?.responseToViewState()
                callback.onResult(
                    pets?.pets?.toMutableList() ?: emptyList(),
                    null,
                    petsResponse.body()?.pagination?.currentPage?.plus(1) ?: 2
                )
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PetListViewState.Pet>
    ) {
        viewModel.viewModelScope.launch {
            viewModel.isLoading.postValue(true)
            val petsResponse = api.getPetListing(
                type = searchModel.animal,
                breed = searchModel.breed,
                size = searchModel.size,
                gender = searchModel.sex,
                age = searchModel.age,
                location = searchModel.location ?: "75001",
                sort = "distance",
                page = params.key
            )
            if (petsResponse.isSuccessful) {
                viewModel.isLoading.postValue(false)
                viewModel.petResponse.addAll(petsResponse.body()?.animals!!)
                val pets = petsResponse.body()?.responseToViewState()
                callback.onResult(
                    pets?.pets?.toMutableList() ?: emptyList(),
                    petsResponse.body()?.pagination?.currentPage?.plus(1) ?: params.key
                )
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PetListViewState.Pet>
    ) { }
}