package com.chaseolson.pets.home

import com.chaseolson.pets.home.model.PetFinderResponse
import com.chaseolson.pets.home.model.PetListItemViewModel
import retrofit2.Call
import retrofit2.Response
import java.io.EOFException

class HomeScreenLogic(private val listener: Listener, private val api: PetListingApi) {

    interface Listener {
        fun present(vm: PetListItemViewModel?)
        fun presentError(error: String)
    }

    fun makeCall() {
        // I get EOFException randomly and sometimes multiple times in a row.
        // This is a Band-Aid solution until a better one pops up.
        try {
            val callback = api.getPetsList(location = "75001", count = 40, format = "xml")
            val response = callback.execute()
            if (response.isSuccessful) {
                val vm = responseToViewModel(response.body()?.pet)
                listener.present(vm)
            } else {
                listener.presentError(response.errorBody()?.string() ?: response.code().toString())
            }
        } catch (e: EOFException) {
            try {
                val callback = api.getPetsList(location = "75001", count = 40, format = "xml")
                val response = callback.execute()
                if (response.isSuccessful) {
                    val vm = responseToViewModel(response.body()?.pet)
                    listener.present(vm)
                } else {
                    listener.presentError(response.errorBody()?.string()
                            ?: response.code().toString())
                }
            } catch (e: EOFException) {
                val callback = api.getPetsList(location = "75001", count = 40, format = "xml")
                val response = callback.execute()
                if (response.isSuccessful) {
                    val vm = responseToViewModel(response.body()?.pet)
                    listener.present(vm)
                } else {
                    listener.presentError(response.errorBody()?.string()
                            ?: response.code().toString())
                }
            }
        }
    }

    companion object {
        fun responseToViewModel(pets: List<PetFinderResponse.Pet>?): PetListItemViewModel? {
            val petList = mutableListOf<PetListItemViewModel.Pet>()
            for (pet in pets ?: return null) {
                petList.add(
                        PetListItemViewModel.Pet(
                                name = pet.name,
                                age = pet.age,
                                gender = pet.sex,
                                breed = pet.breed,
                                images = responseImagesToImagesList(pet.photos, pet.animal)
                        )
                )
            }
            return PetListItemViewModel(petList)
        }

        private fun responseImagesToImagesList(photos: List<PetFinderResponse.Pet.Photo>, animal: String): List<String> {
            val photosList = photos.filter { it.size == "x" }.map { it.photo }

            if (photosList.isNullOrEmpty()) {
                if (animal == "Dog") {
                    return listOf("file://res/drawable/dog_silhouette.jpg")
                } else if (animal == "Cat") {
                    return listOf("file://res/drawable/cat_silhouette.jpg")
                }
            }

            return photosList
        }
    }
}