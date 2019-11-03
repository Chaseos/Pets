package com.chaseolson.pets.petdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.chaseolson.pets.R
import com.chaseolson.pets.oldstuff.MainActivityViewModel
import com.chaseolson.pets.databinding.PetDetailsFragmentBinding
import com.chaseolson.pets.oldstuff.HomeScreen.Companion.PET_ID

class PetDetailsFragment2 : Fragment() {

    private val vmPetDetails: PetDetailsViewModel2 by viewModels()
    private val vmNavGraph: MainActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<PetDetailsFragmentBinding>(inflater, R.layout.pet_details_fragment, container, false)
        binding.lifecycleOwner = this

        binding.viewState = vmPetDetails.viewState.value

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(PET_ID)?.run {
            vmPetDetails.setup(this)
        }
//        vmPetDetails.setup(vmNavGraph.petId)
    }
}