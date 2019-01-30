package com.chaseolson.pets.home.presenter

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetListItemViewModel

class HomeScreenPresenter {

    interface Listener {
        fun swipeRefresh()
    }

    class Container(val root: View, private val listener: Listener) {
        val swipeLayout: SwipeRefreshLayout = root.findViewById(R.id.home_swipelayout)
        val progressBar: ProgressBar = root.findViewById(R.id.home_progressBar)
        val petAdapter = PetRecyclerViewAdapter()
        private val petRecycler: RecyclerView = root.findViewById(R.id.pet_recyclerView)

        init {
            swipeLayout.setOnRefreshListener { listener.swipeRefresh() }
            petRecycler.layoutManager = GridLayoutManager(root.context, 2)
            petRecycler.adapter = petAdapter
        }
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