package com.chaseolson.pets.home

import com.chaseolson.pets.core.*
import com.chaseolson.pets.home.model.PetFinderResponse
import com.chaseolson.pets.home.model.PetListItemViewModel

class HomeScreenLogic {
    companion object {
        fun responseToViewModel(pets: PetFinderResponse?): PetListItemViewModel? {
            val petList = mutableListOf<PetListItemViewModel.Pet>()
            pets?.pet?.distinct()?.forEach { pet ->
                petList.add(
                        PetListItemViewModel.Pet(
                                name = pet.name.filterName(),
                                city = pet.sex.genderAndLocationToString(pet.contact.city),
                                images = pet.photos?.run { this.filterImagesList() } ?: emptyList(),
                                backupImage = pet.animal.animalToBackupImage(),
                                offset = pets.lastOffset
                        )
                )
            }
            return PetListItemViewModel(petList.distinct())
        }
    }
}