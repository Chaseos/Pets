package com.chaseolson.pets.home

import com.chaseolson.pets.home.model.PetFinderResponse
import com.chaseolson.pets.home.model.PetListItemViewModel
import com.chaseolson.pets.home.retrofit.PetListingApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenLogic(private val listener: Listener, private val api: PetListingApi) {

    interface Listener {
        fun present(vm: PetListItemViewModel?)
        fun presentError(error: String)
    }

    fun setup() {
        val callback = api.getPetsList(location = "75001", count = 40, format = "xml").execute()
        if (callback.isSuccessful) {
            val vm = responseToViewModel(callback.body()?.pet)
            listener.present(vm)
        } else {
            listener.presentError(callback.errorBody()?.string() ?: callback.code().toString())
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

        private fun responseImagesToImagesList(photos: List<PetFinderResponse.Pet.Photo>?, animal: String?): List<String?>? {
            val photosList = photos?.filter { it.size == "x" }?.map { it.photo }

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