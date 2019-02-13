package com.chaseolson.pets.home.presenter

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.PopupWindow
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetListItemViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.google.android.material.card.MaterialCardView
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
        val petAdapter = PetRecyclerViewAdapter()
        val petRecycler: RecyclerView = root.pet_recyclerView

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

            val locationLayout = LayoutInflater.from(root.context).inflate(R.layout.location_dialog, null)
            location.setOnClickListener {
                val popupWindow = PopupWindow(locationLayout, 700, 175, true)
                popupWindow.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
                popupWindow.animationStyle = R.style.Animation_MaterialComponents_BottomSheetDialog

                val search = popupWindow.contentView.search_icon
                val zipCode = popupWindow.contentView.zip_code
                val zipCodeLayout = popupWindow.contentView.zip_code_layout
                zipCode.isFocusableInTouchMode = true
                zipCode.requestFocus()
                search.setOnClickListener {
                    when {
                        zipCode.text.toString().isBlank() -> popupWindow.dismiss()
                        zipCode.text.toString().length != 5 -> zipCodeLayout.error = "Must be 5 numbers"
                        else -> {
                            petRecycler.scrollToPosition(0)
                            location.text = "NEAR ${zipCode.text.toString()}"
                            listener.zipCodeSearch(zipCode.text.toString())
                            zipCodeLayout.error = null
                            zipCodeLayout.isErrorEnabled = false
                            zipCode.text?.clear()
                            popupWindow.dismiss()
                        }
                    }
                }
                zipCode.setOnEditorActionListener { v, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        when {
                            zipCode.text.toString().isBlank() -> popupWindow.dismiss()
                            zipCode.text.toString().length != 5 -> zipCodeLayout.error = "Must be 5 numbers"
                            else -> {
                                petRecycler.scrollToPosition(0)
                                location.text = "NEAR ${zipCode.text.toString()}"
                                listener.zipCodeSearch(zipCode.text.toString())
                                zipCodeLayout.error = null
                                zipCodeLayout.isErrorEnabled = false
                                zipCode.text?.clear()
                                popupWindow.dismiss()
                            }
                        }
                    }
                    false
                }
                popupWindow.showAsDropDown(location, -230, 0)
            }

            val myLocatonIcon = locationLayout.my_location_icon
            myLocatonIcon.setOnClickListener {
                val locationCallback = object : LocationListener {
                    override fun onLocationChanged(location: Location?) {
                        location?.run {
                            val geoCoder = Geocoder(root.context, Locale.getDefault())
                            val address = geoCoder.getFromLocation(latitude, longitude, 1)[0]
                            locationLayout.zip_code.setText(address.postalCode)
                        }
                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                        Toast.makeText(root.context, "onStatusChanged", Toast.LENGTH_SHORT).show()
                    }

                    override fun onProviderEnabled(provider: String?) {
                        Toast.makeText(root.context, "onProviderEnabled", Toast.LENGTH_SHORT).show()
                    }

                    override fun onProviderDisabled(provider: String?) {
                        Toast.makeText(root.context, "onProviderDisabled", Toast.LENGTH_SHORT).show()
                    }

                }

                if (ContextCompat.checkSelfPermission(root.context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    (root.context.getSystemService(Context.LOCATION_SERVICE) as LocationManager).requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0F, locationCallback)
                } else {
                    listener.requestLocationPermission()
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