package com.chaseolson.pets.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chaseolson.pets.R
import com.chaseolson.pets.home.presenter.HomeScreenPresenter
import com.chaseolson.pets.home.presenter.PetRecyclerItemPresenter

class HomeScreen : Fragment() {

    private lateinit var avm: HomeScreenAVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        avm = ViewModelProviders.of(this).get(HomeScreenAVM::class.java)

        avm.setup()

        avm.present().observe(this, Observer { HomeScreenPresenter.present(HomeScreenPresenter.Container(view, listener), it) })

        avm.presentError().observe(this, Observer { HomeScreenPresenter.presentError(HomeScreenPresenter.Container(view, listener), it) })

        super.onViewCreated(view, savedInstanceState)
    }

    private val listener = object: PetRecyclerItemPresenter.Listener {
        override fun onPetClicked() {
//            startActivity()
        }
    }
}
