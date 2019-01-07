package com.chaseolson.pets.home

import com.chaseolson.pets.home.model.PetFinderResponse
import com.chaseolson.pets.home.model.PetListItemViewModel
import com.chaseolson.pets.home.retrofit.PetListingApi
import retrofit2.Call
import retrofit2.Response

class HomeScreenLogic(private val listener: Listener, private val api: PetListingApi) {

    interface Listener {
        fun present(vm: PetListItemViewModel?)
        fun presentError(error: String)
    }

    private val callback = object : retrofit2.Callback<PetFinderResponse> {
        override fun onFailure(call: Call<PetFinderResponse>, t: Throwable) {
            listener.presentError(t.localizedMessage ?: t.message ?: "Error")
        }

        override fun onResponse(call: Call<PetFinderResponse>, response: Response<PetFinderResponse>) {
            response.isSuccessful.let {
                val pet = response.body()?.pet
                val vm = responseToViewModel(pet)
                listener.present(vm)
            }
        }
    }

    fun setup() {
        api.getPetsList(location = "75001", count = 20, format = "xml").enqueue(callback)
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
                        images = responseImagesToImagesList(pet.photos)
                    )
                )
            }
            return PetListItemViewModel(petList)
        }

        private fun responseImagesToImagesList(photos: List<PetFinderResponse.Pet.Photo>?): List<String?>? {
            return photos?.filter { it.size == "x" }?.map { it.photo }
        }
    }
}