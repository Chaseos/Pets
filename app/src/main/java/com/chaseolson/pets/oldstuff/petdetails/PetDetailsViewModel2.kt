package com.chaseolson.pets.oldstuff.petdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chaseolson.pets.core.ResultWrapper

//TODO Build motionlayout
//TODO Build builder
//TODO Remove listener
//TODO Add Koin?
//TODO Remove present

class PetDetailsViewModel2 : ViewModel() {
    val listener = object : PetDetailsRepo.Listener {
        override fun present(vs: PetDetailsViewState) {
            present.value = vs
        }
    }

    private var currentViewState = MutableLiveData<PetDetailsViewState>()
    val viewState: LiveData<PetDetailsViewState> = currentViewState

    private val present = MutableLiveData<PetDetailsViewState>()
    var petDetailsRepo: PetDetailsRepo = PetDetailsRepo(listener)

    // Functions called
    fun setup(petId: Int?) {
        if (currentViewState.value == null) currentViewState.value = PetDetailsViewState(id = petId)

//        petId?.run {
//            viewModelScope.async {
//                currentViewState.value = buildPets2(petDetailsRepo.getPetDetailsData2(petId))
//            }
//        }

//        petId?.run {
//            currentViewState.value = Transformations.map(petDetailsRepo.getPetDetailsData(petId)) {
//                buildPets(it) }.value
//        }
    }

    fun buildPets(petDetailsData: ResultWrapper<PetDetailsResponse>): PetDetailsViewState {
        return responseToViewModel(petDetailsData.data?.pet)
//        when (petDetailsData.status) {
//            Status.LOADING -> {}
//            Status.SUCCESS -> currentViewState.postValue(oldResponseToViewModel(petDetailsData.data?.pet))
//            Status.ERROR -> {}
//        }
//        return PetDetailsViewState()
    }

    fun buildPets2(petDetailsData: PetDetailsResponse?): PetDetailsViewState {
        return responseToViewModel(petDetailsData?.pet)
    }

    fun responseToViewModel(petDetails: PetDetailsResponse.Pet?): PetDetailsViewState {
        return PetDetailsViewState(
                id = petDetails?.id,
                image = petDetails?.photos?.get(0)?.photo
//                .run { this.filterImagesList() } ?: emptyList()
        )
    }

}