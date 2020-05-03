package com.chaseolson.pets.main

import androidx.lifecycle.ViewModel
import com.chaseolson.pets.details.PetDetailViewState

class MainActivityViewModel : ViewModel() {
    var selectedPet: PetDetailViewState? = null
}