package com.chaseolson.pets.petdetails

import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pet_details_fragment.view.*

class PetDetailsPresenter {
    interface Listener

    class Container(val root: View, val listener: Listener) {
        val petImage = root.pet_image

        init {

        }
    }

    fun present(container: Container, vs: PetDetailsViewState) {
        vs.images.getOrNull(0)?.run { Picasso.get().load(this).into(container.petImage) }
    }
}