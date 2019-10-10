package com.chaseolson.pets.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.chaseolson.pets.R
import com.chaseolson.pets.core.MainActivityViewModel
import com.chaseolson.pets.home.model.SearchModel
import com.chaseolson.pets.home.presenter.HomeScreenPresenter
import com.chaseolson.pets.home.presenter.PetRecyclerItemPresenter
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.android.synthetic.main.location_dialog.view.*

class HomeScreen : Fragment(), HomeScreenPresenter.Listener, SearchDialog.SearchDialogListener, PetRecyclerItemPresenter.Listener {
    val LOCATION_REQUEST_CODE = 100

    companion object { val PET_ID = "petId" }

    private val viewModel: HomeScreenViewModel by lazy { ViewModelProviders.of(this).get(HomeScreenViewModel::class.java) }
    private val vmMainActivity: MainActivityViewModel by lazy { ViewModelProviders.of(this).get(MainActivityViewModel::class.java) }
    private lateinit var container: HomeScreenPresenter.Container

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        container = HomeScreenPresenter.Container(view, this, this)

        viewModel.presentError().observe(this, Observer { HomeScreenPresenter.presentError(container, it) })

        viewModel.pets().observe(this, Observer { HomeScreenPresenter.present(container, it) })

        val searchDialog = SearchDialog()
        searchDialog.setTargetFragment(this, 0)
        home_search_icon.setOnClickListener {
            fragmentManager?.run {
                if (findFragmentByTag(SearchDialog.TAG) == null) searchDialog.show(this, SearchDialog.TAG)
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun swipeRefresh() {
        viewModel.swipeRefresh()
    }

    override fun zipCodeSearch(zipCode: String) {
        viewModel.searchByZipCode(zipCode)
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

    override fun search(searchModel: SearchModel) {
        viewModel.search(searchModel)
    }

    override fun petOnClick(id: Int, petImage: View) {
//        val extras = FragmentNavigatorExtras(
//            petImage to petImage.transitionName
//        )
//
        val args = Bundle()
        args.putInt(PET_ID, id)
        vmMainActivity.petId = id
        findNavController(this).navigate(R.id.action_homeScreen_to_petDetailsFragment2, args, null, null)
    }
}
