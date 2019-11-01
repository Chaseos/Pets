package com.chaseolson.pets.home

import com.chaseolson.pets.core.*
import com.chaseolson.pets.home.model.NewPetFinderResponse
import com.chaseolson.pets.home.model.PetFinderResponse
import com.chaseolson.pets.home.model.PetListItemViewState

class HomeScreenRepo {
    companion object {
        fun responseToViewModel(pets: PetFinderResponse?): PetListItemViewState? {
            val petList = mutableListOf<PetListItemViewState.Pet>()
            pets?.pet?.distinctBy { pet -> pet.id }?.forEach { pet ->
                petList.add(
                        PetListItemViewState.Pet(
                                id = pet.id,
                                name = pet.name.filterName(),
                                city = pet.sex.genderAndLocationToString(pet.contact.city),
                                images = pet.photos?.run { this.filterImagesList() } ?: emptyList(),
                                backupImage = pet.animal.animalToBackupImage(),
                                offset = pets.lastOffset
                        )
                )
            }
            return PetListItemViewState(petList)
        }

        fun responseToViewModelNew(pets: NewPetFinderResponse?): PetListItemViewState? {
            val newList = pets?.animals?.distinctBy { it.id }?.map { pet ->
                PetListItemViewState.Pet(
                        id = pet.id,
                        name = pet.name?.filterName() ?: "No Name",
                        city = pet.gender?.genderAndLocationToString(pet.contact.address.city),
                        images = pet.photos.filterImagesList2(),
                        backupImage = pet.type.animalToBackupImage(),
                        offset = pets.pagination?.currentPage ?: 0
                )
            }

            return PetListItemViewState(newList)
        }
    }
}