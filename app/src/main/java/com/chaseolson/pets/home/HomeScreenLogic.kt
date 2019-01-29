package com.chaseolson.pets.home

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
                        breed = pet.breed,
                        images = responseImagesToImagesList(pet.photos ?: emptyList(), pet.animal),
                        offset = pets.lastOffset
                    )
                )
            }
            return PetListItemViewModel(petList)
        }

        private fun responseImagesToImagesList(
            photos: List<PetFinderResponse.Pet.Photo>,
            animal: String
        ): List<String> {
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