package com.chaseolson.pets.home

import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import com.chaseolson.pets.home.model.PetListItemViewModel
import java.io.EOFException

class PetFeed(val listener: PetFeed.Listener, val api: PetListingApi, val searchModel: SearchModel) :
    ItemKeyedDataSource<Int, PetListItemViewModel.Pet>() {

    val CALL_TRIES = 10
    var currentTries = 0

    interface Listener {
        fun presentError(error: String)
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<PetListItemViewModel.Pet>) {
        try {
            val request = api.getPetsList(
                location = searchModel.location ?: "75001",
                count = params.requestedLoadSize
            ).execute()
            currentTries = 0
            val pets = HomeScreenLogic.responseToViewModel(request.body())
            callback.onResult(pets?.pets?.toMutableList() ?: emptyList())
        } catch (e: EOFException) {
            if (currentTries < CALL_TRIES) {
                currentTries++
                loadInitial(params, callback)
            } else {
                listener.presentError("Unable to load data")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<PetListItemViewModel.Pet>) {
        try {
            val request = api.getPetsList(
                location = searchModel.location ?: "75001",
                offset = params.key,
                count = params.requestedLoadSize
            ).execute()
            currentTries = 0
            val pets = HomeScreenLogic.responseToViewModel(request.body())
            callback.onResult(pets?.pets?.toMutableList() ?: emptyList())
        } catch (e: EOFException) {
            if (currentTries < CALL_TRIES) {
                currentTries++
                loadAfter(params, callback)
            } else {
                listener.presentError("Unable to load data")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<PetListItemViewModel.Pet>) {}

    override fun getKey(item: PetListItemViewModel.Pet): Int = item.offset ?: 0

}

class PetDataSourceFactory(val listener: PetFeed.Listener, var searchModel: SearchModel) :
    DataSource.Factory<Int, PetListItemViewModel.Pet>() {
    override fun create(): DataSource<Int, PetListItemViewModel.Pet> {
        return PetFeed(listener, PetListingApiImpl(), searchModel)
    }
}