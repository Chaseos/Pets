package com.chaseolson.pets.home.presenter

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.chaseolson.pets.home.HomeScreenRepo
import com.chaseolson.pets.home.model.PetListItemViewState
import com.chaseolson.pets.home.model.SearchModel
import com.chaseolson.pets.repo.MobileEndpointsNew
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PetFeedNew(val listener: Listener, val api: MobileEndpointsNew, val searchModel: SearchModel, val scope: CoroutineScope) : PageKeyedDataSource<Int, PetListItemViewState.Pet>() {
    //    val CALL_TRIES = 5
//    var currentTries = 0

    interface Listener {
        fun presentError(error: String)
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PetListItemViewState.Pet>) {
        scope.launch {
            val petsResponse = api.getPetListingNew(
                    type = searchModel.animal,
                    breed = searchModel.breed,
                    size = searchModel.size,
                    gender = searchModel.sex,
                    age = searchModel.age,
                    location = searchModel.location ?: "75001"
            )
            if (petsResponse.isSuccessful) {
                val pets = HomeScreenRepo.responseToViewModelNew(petsResponse.body())
                callback.onResult(pets?.pets?.toMutableList() ?: emptyList(), 0, 1)
            } else listener.presentError("Unable to load data")
//            currentTries = 0
//            if (currentTries < CALL_TRIES) {
//                currentTries++
//                loadInitial(params, callback)
//            } else {
//                listener.presentError("Unable to load data")
//            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PetListItemViewState.Pet>) {
        scope.launch {
            val petsResponse = api.getPetListingNew(
                    type = searchModel.animal,
                    breed = searchModel.breed,
                    size = searchModel.size,
                    gender = searchModel.sex,
                    age = searchModel.age,
                    location = searchModel.location ?: "75001",
                    page = params.key
            )
            if (petsResponse.isSuccessful) {
                val pets = HomeScreenRepo.responseToViewModelNew(petsResponse.body())
                callback.onResult(pets?.pets?.toMutableList() ?: emptyList(), params.key)
            } else listener.presentError("Unable to load data")

//        currentTries = 0
//        val pets = HomeScreenRepo.responseToViewModelNew(request.body())
//        callback.onResult(pets?.pets?.toMutableList() ?: emptyList())
//        listener.presentError("Unable to load data")
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PetListItemViewState.Pet>) {}
}


class PetDataSourceFactoryNew(val listener: PetFeedNew.Listener, val api: MobileEndpointsNew, var searchModel: SearchModel, val scope: CoroutineScope) : DataSource.Factory<Int, PetListItemViewState.Pet>() {
    override fun create(): DataSource<Int, PetListItemViewState.Pet> {
        return PetFeedNew(listener, api, searchModel, scope)
    }
}