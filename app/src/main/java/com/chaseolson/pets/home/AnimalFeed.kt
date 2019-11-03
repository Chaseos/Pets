package com.chaseolson.pets.home

import androidx.paging.PageKeyedDataSource
import com.chaseolson.pets.home.models.PetListItemViewState
import com.chaseolson.pets.home.models.responseToViewModel
import com.chaseolson.pets.oldstuff.SearchModel
import com.chaseolson.pets.repo.MobileEndpointsNew
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PetFeedNew(val api: MobileEndpointsNew,
                 val searchModel: SearchModel,
                 val viewModel: HomeScreenViewModel,
                 val scope: CoroutineScope) : PageKeyedDataSource<Int, PetListItemViewState.Pet>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PetListItemViewState.Pet>) {
        scope.launch {
            val petsResponse = api.getPetListingNew(
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
                val pets = petsResponse.body()?.responseToViewModel()
                callback.onResult(pets?.pets?.toMutableList() ?: emptyList(), null, petsResponse.body()?.pagination?.currentPage?.plus(1) ?: 2)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PetListItemViewState.Pet>) {
        scope.launch {
            viewModel.isLoading.postValue(true)
            val petsResponse = api.getPetListingNew(
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
                val pets = petsResponse.body()?.responseToViewModel()
                callback.onResult(pets?.pets?.toMutableList() ?: emptyList(), petsResponse.body()?.pagination?.currentPage?.plus(1) ?: params.key)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PetListItemViewState.Pet>) {}

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}