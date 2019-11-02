package com.chaseolson.pets.home.presenter

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.chaseolson.pets.home.HomeScreenRepo
import com.chaseolson.pets.home.model.NewPetListItemViewState
import com.chaseolson.pets.home.model.PetListItemViewState
import com.chaseolson.pets.home.model.SearchModel
import com.chaseolson.pets.repo.MobileEndpointsNew
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PetFeedNew(val api: MobileEndpointsNew, val searchModel: SearchModel, val scope: CoroutineScope) : PageKeyedDataSource<Int, NewPetListItemViewState.NewPet>() {
    //    val CALL_TRIES = 5
//    var currentTries = 0

//    interface Listener {
//        fun presentError(error: String)
//    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, NewPetListItemViewState.NewPet>) {
        scope.launch {
            val petsResponse = api.getPetListingNew(
                    type = searchModel.animal,
                    breed = searchModel.breed,
                    size = searchModel.size,
                    gender = searchModel.sex,
                    age = searchModel.age,
                    location = searchModel.location ?: "75001",
                    sort = "distance"
            )
            if (petsResponse.isSuccessful) {
                val pets = HomeScreenRepo.responseToViewModelNew(petsResponse.body())
                callback.onResult(pets?.pets?.toMutableList() ?: emptyList(), 1, 2)
            }
//            currentTries = 0
//            if (currentTries < CALL_TRIES) {
//                currentTries++
//                loadInitial(params, callback)
//            } else {
//                listener.presentError("Unable to load data")
//            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewPetListItemViewState.NewPet>) {
        scope.launch {
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
                val pets = HomeScreenRepo.responseToViewModelNew(petsResponse.body())
                callback.onResult(pets?.pets?.toMutableList() ?: emptyList(), params.key)
            }

//        currentTries = 0
//        val pets = HomeScreenRepo.responseToViewModelNew(request.body())
//        callback.onResult(pets?.pets?.toMutableList() ?: emptyList())
//        listener.presentError("Unable to load data")
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NewPetListItemViewState.NewPet>) {}
}


class PetDataSourceFactoryNew(val api: MobileEndpointsNew, var searchModel: SearchModel, val scope: CoroutineScope) : DataSource.Factory<Int, NewPetListItemViewState.NewPet>() {
    override fun create(): DataSource<Int, NewPetListItemViewState.NewPet> {
        return PetFeedNew(api, searchModel, scope)
    }
}