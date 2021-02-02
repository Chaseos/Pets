package com.chaseolson.pets.ui.main

import androidx.lifecycle.ViewModel
import com.chaseolson.pets.ui.details.PetDetailViewState

class MainActivityViewModel : ViewModel() {
    var selectedPet: PetDetailViewState? = null
}