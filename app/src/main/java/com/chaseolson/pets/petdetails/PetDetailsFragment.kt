package com.chaseolson.pets.petdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chaseolson.pets.R
import com.chaseolson.pets.core.MainActivityViewModel
import com.chaseolson.pets.home.HomeScreen.Companion.PET_ID
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetDetailsFragment: Fragment(), PetDetailsPresenter.Listener {

    private val viewModel: PetDetailsViewModel by lazy { ViewModelProviders.of(this).get(PetDetailsViewModel::class.java) }
    private val vmMainActivity: MainActivityViewModel by lazy { ViewModelProviders.of(this).get(MainActivityViewModel::class.java) }

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