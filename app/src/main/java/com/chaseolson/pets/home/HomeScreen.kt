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
import kotlinx.android.synthetic.main.fragment_home_screen.*

class HomeScreen : Fragment() {

    private val avm: HomeScreenAVM by lazy { ViewModelProviders.of(this).get(HomeScreenAVM::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val container = HomeScreenPresenter.Container(view)

        avm.presentError().observe(this, Observer {
            home_progressBar.visibility = View.GONE
            HomeScreenPresenter.presentError(HomeScreenPresenter.Container(view), it)
        })

        avm.pets().observe(this, Observer {
            HomeScreenPresenter.present(container, it)
        })

        super.onViewCreated(view, savedInstanceState)
    }
}
