package com.chaseolson.pets.home.presenter

import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetListItemViewModel
import com.google.android.material.textfield.TextInputEditText

class HomeScreenPresenter {

    interface Listener {
        fun swipeRefresh()
    }

    class Container(val root: View, private val listener: Listener) {
        val swipeLayout: SwipeRefreshLayout = root.findViewById(R.id.home_swipelayout)
        val searchBar: TextInputEditText = root.findViewById(R.id.search_bar)
        val progressBar: ProgressBar = root.findViewById(R.id.home_progressBar)
        val petAdapter = PetRecyclerViewAdapter()
        private val petRecycler: RecyclerView = root.findViewById(R.id.pet_recyclerView)

        init {
            swipeLayout.setOnRefreshListener { listener.swipeRefresh() }
            searchBar.setOnEditorActionListener { v, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(v.windowToken, 0)
                        Toast.makeText(v.context, "You did it!", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
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