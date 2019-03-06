package com.chaseolson.pets.home.presenter

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.PopupWindow
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetListItemViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_home_screen.view.*
import kotlinx.android.synthetic.main.location_dialog.view.*
import kotlinx.coroutines.*
import java.util.*


class HomeScreenPresenter {

    class Container(val root: View, private val listener: Listener) {
        val swipeLayout: SwipeRefreshLayout = root.home_swipelayout
        val progressBar: ProgressBar = root.home_progressBar
        val scrollToTopButton: MaterialCardView = root.scroll_to_top_button
        val appBarLayout: AppBarLayout = root.pet_appbar_layout
        val location: TextView = root.location
        val bottomBar: BottomNavigationView = root.home_bottom_nav
        val petAdapter = PetRecyclerViewAdapter()
        val petRecycler: RecyclerView = root.pet_recyclerView
        var locationLayout = LayoutInflater.from(root.context).inflate(R.layout.location_dialog, null)
        val myLocatonIcon = locationLayout.my_location_icon

        init {
            swipeLayout.setOnRefreshListener { listener.swipeRefresh() }

            scrollToTopButton.setOnClickListener { petRecycler.smoothScrollToPosition(0) }

            petRecycler.layoutManager = GridLayoutManager(root.context, 2)
            petRecycler.adapter = petAdapter
            petRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = petRecycler.layoutManager as GridLayoutManager
                    if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                        val params = scrollToTopButton.layoutParams as CoordinatorLayout.LayoutParams
                        val behavior = params.behavior as HideBottomViewOnScrollBehavior<MaterialCardView>
                        behavior.slideDown(scrollToTopButton)

                        appBarLayout.setExpanded(true)
                    }

                    GlobalScope.launch(Dispatchers.Main) {
                        if (petRecycler.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                            repeat(4) {
                                delay(500)
                                if (petRecycler.scrollState != RecyclerView.SCROLL_STATE_IDLE) cancel()
                            }
                            if (petRecycler.scrollState == RecyclerView.SCROLL_STATE_IDLE) appBarLayout.setExpanded(true)
                        }
                    }
                }
            })

            location.setOnClickListener {
                locationOnClick()
            }

            bottomBar.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.action_home -> {
                        if (it.isChecked) {
                            petRecycler.smoothScrollToPosition(0)
                            appBarLayout.setExpanded(true)
                        }
                        true
                    }
                    else -> {
                        it.isChecked = true
                        true
                    }
                }
            }
        }

        private fun locationOnClick() {
            val popupWindow = PopupWindow(locationLayout, 700, 175, true)
            popupWindow.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
            popupWindow.animationStyle = R.style.Animation_MaterialComponents_BottomSheetDialog

            val search = popupWindow.contentView.search_icon
            val zipCode = popupWindow.contentView.zip_code
            val zipCodeLayout = popupWindow.contentView.zip_code_layout
            zipCode.requestFocus()
            search.setOnClickListener {
                locationSearchOnClick(zipCode, popupWindow, zipCodeLayout)
            }
            zipCode.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    locationSearchOnClick(zipCode, popupWindow, zipCodeLayout)
                }
                false
            }
            popupWindow.showAsDropDown(location, -230, 0)
            popupWindow.setOnDismissListener {
                zipCodeLayout.error = null
                zipCodeLayout.isErrorEnabled = false
                zipCode.text?.clear()
            }

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult?) {
                    p0?.lastLocation?.run {
                        val geoCoder = Geocoder(root.context, Locale.getDefault())
                        val address = geoCoder.getFromLocation(latitude, longitude, 1)[0]
                        zipCode.setText(address.postalCode)
                    }
                    super.onLocationResult(p0)
                }
            }

            val locationRequest = LocationRequest()
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(root.context as Activity)

            myLocatonIcon.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                                root.context,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                ) {
                    fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
                } else {
                    listener.requestLocationPermission()
                }
            }
        }

        private fun locationSearchOnClick(zipCode: TextInputEditText, popupWindow: PopupWindow, zipCodeLayout: TextInputLayout) {
            when {
                zipCode.text.toString().isBlank() -> popupWindow.dismiss()
                zipCode.text.toString().length != 5 -> zipCodeLayout.error = "Must be 5 numbers"
                else -> {
                    location.text = "NEAR ${zipCode.text.toString()}"
                    listener.zipCodeSearch(zipCode.text.toString())
                    zipCodeLayout.error = null
                    zipCodeLayout.isErrorEnabled = false
                    zipCode.text?.clear()
                    GlobalScope.launch(Dispatchers.Main) {
                        delay(500)
                        petRecycler.scrollToPosition(0)
                    }
                    popupWindow.dismiss()
                }
            }
        }
    }

    interface Listener {
        fun swipeRefresh()
        fun zipCodeSearch(zipCode: String)
        fun requestLocationPermission()
    }

    companion object {

        fun present(container: Container, pets: PagedList<PetListItemViewModel.Pet>) {
            container.swipeLayout.isRefreshing = false
            container.progressBar.visibility = View.GONE
            container.petAdapter.submitList(pets)
        }

        fun presentError(container: Container, error: String) {
            container.progressBar.visibility = View.GONE
            val dialog = AlertDialog.Builder(container.root.context)
                    .setTitle(error)
                    .setPositiveButton("Ok") { _, _ ->
                        //TODO
                    }
                    .create()
            dialog.show()
        }
    }
}