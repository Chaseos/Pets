package com.chaseolson.pets.oldstuff

import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import java.io.EOFException

class PetFeed(val listener: Listener, val api: PetListingApi, val searchModel: SearchModel) : ItemKeyedDataSource<Int, OldPetListItemViewState.Pet>() {

    val CALL_TRIES = 5
    var currentTries = 0

    interface Listener {
        fun presentError(error: String)
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<OldPetListItemViewState.Pet>) {
        try {
            val request = api.getPetsList(
                    animal = searchModel.animal,
                    breed = searchModel.breed,
                    size = searchModel.size,
                    sex = searchModel.sex,
                    age = searchModel.age,
                    location = searchModel.location ?: "75001",
                    count = params.requestedLoadSize
            ).execute()
            currentTries = 0
//            val pets = OldHomeScreenRepo.oldResponseToViewModel(request.body())
//            callback.onResult(pets?.pets?.toMutableList() ?: emptyList())
        } catch (e: EOFException) {
            if (currentTries < CALL_TRIES) {
                currentTries++
                loadInitial(params, callback)
            } else {
                listener.presentError("Unable to load data")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<OldPetListItemViewState.Pet>) {
        try {
            val request = api.getPetsList(
                    animal = searchModel.animal,
                    breed = searchModel.breed,
                    size = searchModel.size,
                    sex = searchModel.sex,
                    age = searchModel.age,
                    location = searchModel.location ?: "75001",
                    offset = params.key,
                    count = params.requestedLoadSize
            ).execute()
            currentTries = 0
//            val pets = OldHomeScreenRepo.oldResponseToViewModel(request.body())
//            callback.onResult(pets?.pets?.toMutableList() ?: emptyList())
        } catch (e: EOFException) {
            if (currentTries < CALL_TRIES) {
                currentTries++
                loadAfter(params, callback)
            } else {
                listener.presentError("Unable to load data")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<OldPetListItemViewState.Pet>) {}

    override fun getKey(item: OldPetListItemViewState.Pet): Int = item.offset ?: 0

}

class PetDataSourceFactory(val listener: PetFeed.Listener, var searchModel: SearchModel) : DataSource.Factory<Int, OldPetListItemViewState.Pet>() {
    override fun create(): DataSource<Int, OldPetListItemViewState.Pet> {
        return PetFeed(listener, PetListingApiImpl(), searchModel)
    }
}