package com.chaseolson.pets.home

import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetFinderResponse
import com.chaseolson.pets.home.model.PetListItemViewModel

class HomeScreenLogic {
    companion object {
        fun responseToViewModel(pets: PetFinderResponse?): PetListItemViewModel? {
            val petList = mutableListOf<PetListItemViewModel.Pet>()
            pets?.pet?.forEach { pet ->
                petList.add(
                    PetListItemViewModel.Pet(
                        name = pet.name,
                        age = pet.age,
                        gender = pet.sex,
                        breed = pet.breeds.map { it.breed },
                        images = responseImagesToImagesList(pet.photos ?: emptyList()),
                        backupImage = when {
                            pet.animal == "Cat" -> R.drawable.cat_silhouette
                            else -> R.drawable.dog_silhouette
                        },
                        offset = pets.lastOffset
                    )
                )
            }
            return PetListItemViewModel(petList)
        }

        private fun responseImagesToImagesList(photos: List<PetFinderResponse.Pet.Photo>): List<String> =
            photos.filter { it.size == "pn" }.map { it.photo }
    }
}