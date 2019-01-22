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

    var calls = 0

    private val callback = object : retrofit2.Callback<PetFinderResponse> {
        override fun onFailure(call: Call<PetFinderResponse>, t: Throwable) {
            if (calls < 2) {
                calls++
                call.clone().enqueue(this)
            } else {
                listener.presentError(t.localizedMessage ?: t.message ?: "Error")
            }
        }

        override fun onResponse(call: Call<PetFinderResponse>, response: Response<PetFinderResponse>) {
            response.isSuccessful.let {
                val pet = response.body()?.pet
                val vm = responseToViewModel(pet)
                listener.present(vm)
            }
        }
    }

    fun makeCall() {
        api.getPetsList(location = "75001", count = 50, format = "xml").enqueue(callback)
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