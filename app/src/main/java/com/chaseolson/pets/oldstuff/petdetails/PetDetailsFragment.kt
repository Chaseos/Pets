package com.chaseolson.pets.oldstuff.petdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chaseolson.pets.R
import com.chaseolson.pets.oldstuff.OldMainActivityViewModel
import com.chaseolson.pets.oldstuff.HomeScreen.Companion.PET_ID

class PetDetailsFragment: Fragment(), PetDetailsPresenter.Listener {

    private val viewModel: PetDetailsViewModel by lazy { ViewModelProviders.of(this).get(PetDetailsViewModel::class.java) }
    private val vmMainActivity: OldMainActivityViewModel by lazy { ViewModelProviders.of(this).get(
        OldMainActivityViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pet_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val container = PetDetailsPresenter.Container(view, this)

        viewModel.presentObs.observe(this, Observer { PetDetailsPresenter().present(container, it) })
    }

    override fun onResume() {
        super.onResume()
        arguments?.getInt(PET_ID)?.run {
            viewModel.setup(this)
        }
    }
}