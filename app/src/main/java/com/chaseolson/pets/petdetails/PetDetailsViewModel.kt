package com.chaseolson.pets.petdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PetDetailsViewModel : ViewModel() {
    // LiveData
    private var currentViewState = MutableLiveData<PetDetailsViewState>()
    val viewState: LiveData<PetDetailsViewState> = currentViewState

    // Functions called
    fun setup(petId: Int?) {
        if (currentViewState.value == null) currentViewState.value = PetDetailsViewState(id = petId)

//        currentViewState.value = Transformations.map(flightBookingRepo.getRoutes(RoutesQuery.build())) { buildRoutes(it) }.value
        }

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

//    fun setup(petId: Int) {
//    }
}