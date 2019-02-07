package com.chaseolson.pets.home

import com.chaseolson.pets.core.*
import com.chaseolson.pets.home.model.PetFinderResponse
import com.chaseolson.pets.home.model.PetListItemViewModel

class HomeScreenLogic {
    companion object {
        fun responseToViewModel(pets: PetFinderResponse?): PetListItemViewModel? {
            val petList = mutableListOf<PetListItemViewModel.Pet>()
            pets?.pet?.forEach { pet ->
                petList.add(
                        PetListItemViewModel.Pet(
                                name = pet.name.filterName(),
                                age = pet.age,
                                gender = pet.sex.charGenderToStringGender(),
                                size = pet.size.charSizeToStringSize(),
                                breed = pet.breeds.mapBreedsToList(),
                                images = pet.photos?.run { this.filterImagesList() } ?: emptyList(),
                                backupImage = pet.animal.animalToBackupImage(),
                                city = pet.contact.city,
                                offset = pets.lastOffset
                        )
                )
            }
            return PetListItemViewModel(petList.distinct())
        }
    }
}