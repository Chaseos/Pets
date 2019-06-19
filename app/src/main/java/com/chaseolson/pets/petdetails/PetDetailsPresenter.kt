package com.chaseolson.pets.petdetails

import android.view.View
import kotlinx.android.synthetic.main.pet_details_fragment.view.*

class PetDetailsPresenter {
    interface Listener

    class Container(val root: View, val listener: Listener) {
        val petImage = root.pet_image
    }
}