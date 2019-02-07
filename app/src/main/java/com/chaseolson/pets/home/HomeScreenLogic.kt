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
                                name = filterName(pet.name),
                                age = pet.age,
                                gender = charGenderToStringGender(pet.sex),
                                size = charSizeToStringSize(pet.size),
                                breed = pet.breeds.map { it.breed },
                                images = filterImagesList(pet.photos ?: emptyList()),
                                backupImage = animalToBackupImage(pet.animal),
                                city = pet.contact.city,
                                offset = pets.lastOffset
                        )
                )
            }
            return PetListItemViewModel(petList.distinct())
        }

        private fun filterName(name: String): String {
            val nonNumberOrDollarSignRegex = Regex("[^A-Za-z &()*-]")
            return name.replace(nonNumberOrDollarSignRegex, "")
        }

        private fun filterImagesList(photos: List<PetFinderResponse.Pet.Photo>): List<String> =
                photos.filter { it.size == "pn" }.map { it.photo }

        private fun animalToBackupImage(animal: String): Int = when (animal) {
            "Cat" -> R.drawable.cat_silhouette
            else -> R.drawable.dog_silhouette
        }

        private fun charSizeToStringSize(size: String): String = when (size) {
            "S" -> "Small"
            "M" -> "Medium"
            "L" -> "Large"
            else -> "Size N/A"
        }

        private fun charGenderToStringGender(gender: String): String = when (gender) {
            "F" -> "Female"
            "M" -> "Male"
            else -> "Gender N/A"
        }
    }
}