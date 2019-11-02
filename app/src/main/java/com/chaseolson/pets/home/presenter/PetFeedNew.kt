package com.chaseolson.pets.home.presenter

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.chaseolson.pets.home.HomeScreenRepo
import com.chaseolson.pets.home.model.NewPetListItemViewState
import com.chaseolson.pets.home.model.SearchModel
import com.chaseolson.pets.newhome.HomeScreenViewModel3
import com.chaseolson.pets.repo.MobileEndpointsNew
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PetFeedNew(val api: MobileEndpointsNew,
                 val searchModel: SearchModel,
                 val viewModel: HomeScreenViewModel3,
                 val scope: CoroutineScope) : PageKeyedDataSource<Int, NewPetListItemViewState.NewPet>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, NewPetListItemViewState.NewPet>) {
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
                val pets = HomeScreenRepo.responseToViewModelNew(petsResponse.body())
                callback.onResult(pets?.pets?.toMutableList() ?: emptyList(), null, petsResponse.body()?.pagination?.currentPage?.plus(1) ?: 2)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewPetListItemViewState.NewPet>) {
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
                val pets = HomeScreenRepo.responseToViewModelNew(petsResponse.body())
                callback.onResult(pets?.pets?.toMutableList() ?: emptyList(), petsResponse.body()?.pagination?.currentPage?.plus(1) ?: params.key)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NewPetListItemViewState.NewPet>) {}

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}


class PetDataSourceFactoryNew(val api: MobileEndpointsNew,
                              var searchModel: SearchModel,
                              val viewModel: HomeScreenViewModel3,
                              val scope: CoroutineScope) : DataSource.Factory<Int, NewPetListItemViewState.NewPet>() {

    override fun create(): DataSource<Int, NewPetListItemViewState.NewPet> {
        return PetFeedNew(api, searchModel, viewModel, scope)
    }
}