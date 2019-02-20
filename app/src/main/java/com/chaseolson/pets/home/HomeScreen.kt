package com.chaseolson.pets.home

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chaseolson.pets.R
import com.chaseolson.pets.home.presenter.HomeScreenPresenter
import kotlinx.android.synthetic.main.location_dialog.view.*

class HomeScreen : Fragment(), HomeScreenPresenter.Listener {

    val LOCATION_REQUEST_CODE = 0

    private val avm: HomeScreenAVM by lazy { ViewModelProviders.of(this).get(HomeScreenAVM::class.java) }
    private lateinit var container: HomeScreenPresenter.Container

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        container = HomeScreenPresenter.Container(view, this)

        avm.presentError().observe(this, Observer { HomeScreenPresenter.presentError(container, it) })

        avm.pets().observe(this, Observer { HomeScreenPresenter.present(container, it) })

        super.onViewCreated(view, savedInstanceState)
    }

    override fun swipeRefresh() {
        avm.swipeRefresh()
    }

    override fun zipCodeSearch(zipCode: String) {
        avm.searchByZipCode(zipCode)
    }

    override fun requestLocationPermission() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            LOCATION_REQUEST_CODE -> {
                if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    container.locationLayout.my_location_icon.performClick()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
