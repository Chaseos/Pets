package com.chaseolson.pets.home

import com.chaseolson.pets.home.model.PetFinder
import com.chaseolson.pets.home.model.PetItemViewModel
import com.chaseolson.pets.home.retrofit.PetListingApiImpl

class HomeScreenLogic(private val listener: Listener) {

    interface Listener {
        fun present(vm: PetItemViewModel?)
        fun presentError(error: String)
    }

    private val callback = object : retrofit2.Callback<PetFinder> {
        override fun onFailure(call: retrofit2.Call<PetFinder>, t: Throwable) {
            listener.presentError(t.localizedMessage)
        }

        override fun onResponse(call: retrofit2.Call<PetFinder>, response: retrofit2.Response<PetFinder>) {
            response.isSuccessful.let {
                val pet = response.body()?.pet
                val vm = responseToViewModel(pet)
                listener.present(vm)
            }
        }
    }

    fun setup() {
        PetListingApiImpl().getPetsList(location = "75001", count = 20, format = "xml").enqueue(callback)
    }

    companion object {
        fun responseToViewModel(pets: List<PetFinder.Pet>?): PetItemViewModel? {
            val petList = mutableListOf<PetItemViewModel.Pet>()
            for (pet in pets ?: return null) {
                petList.add(
                    PetItemViewModel.Pet(
                        name = pet.name,
                        age = pet.age,
                        gender = pet.sex,
                        breed = pet.breed,
                        images = responseImagesToImagesList(pet.photos)
                    ))
            }
            return PetItemViewModel(petList)
        }

        private fun responseImagesToImagesList(photos: List<PetFinder.Pet.Photo>?): List<String?>? {
            return photos?.filter { it.size == "x" }?.map { it.photo }
        }
    }
}