package com.chaseolson.pets.oldstuff

import com.chaseolson.pets.core.*
import com.chaseolson.pets.home.models.PetFinderResponse
import com.chaseolson.pets.home.models.PetListItemViewState

class OldHomeScreenRepo {
    companion object {
        fun oldResponseToViewModel(pets: OldPetFinderResponse?): OldPetListItemViewState? {
            val petList = mutableListOf<OldPetListItemViewState.Pet>()
            pets?.pet?.distinctBy { pet -> pet.id }?.forEach { pet ->
                petList.add(
                        OldPetListItemViewState.Pet(
                                id = pet.id,
                                name = pet.name.filterName(),
                                city = pet.sex.genderAndLocationToString(pet.contact.city),
                                images = pet.oldPhotos?.run { this.filterImagesList() } ?: emptyList(),
                                backupImage = pet.animal.animalToBackupImage(),
                                offset = pets.lastOffset
                        )
                )
            }
            return OldPetListItemViewState(petList)
        }
    }
}