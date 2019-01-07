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

class HomeScreen : Fragment() {

    private lateinit var avm: HomeScreenAVM
    private lateinit var presenter: HomeScreenPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        avm = ViewModelProviders.of(this).get(HomeScreenAVM::class.java)
        presenter = HomeScreenPresenter(view)

        avm.setup()

        avm.present().observe(this, Observer { presenter.present(it) })

        avm.presentError().observe(this, Observer { presenter.presentError(it) })

        super.onViewCreated(view, savedInstanceState)
    }
}
