package com.chaseolson.pets.petdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.chaseolson.pets.R

class PetDetailsFragment: Fragment() {

    private val avm: PetDetailsAVM by lazy { ViewModelProviders.of(this).get(PetDetailsAVM::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pet_details_fragment, container, false)
    }
}