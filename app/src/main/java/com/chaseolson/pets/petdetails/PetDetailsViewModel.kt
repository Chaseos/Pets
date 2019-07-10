package com.chaseolson.pets.petdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PetDetailsViewModel : ViewModel() {
    var petDetailsRepo: PetDetailsRepo? = null
    private val present = MutableLiveData<PetDetailsViewState>()

    val listener = object : PetDetailsRepo.Listener {
        override fun present(vs: PetDetailsViewState) {
            present.value = vs
        }
    }

    init {
        petDetailsRepo = PetDetailsRepo(listener)
    }

    var presentObs: LiveData<PetDetailsViewState> = present

    fun setup(petId: Int) {
        petDetailsRepo?.setup(petId)
    }
}