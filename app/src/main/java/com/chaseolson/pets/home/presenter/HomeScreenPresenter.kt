package com.chaseolson.pets.home.presenter

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetListItemViewModel

class HomeScreenPresenter {

    class Container(val root: View) {

        val petAdapter = PetRecyclerViewAdapter()
        val petRecycler: RecyclerView = root.findViewById(R.id.pet_recyclerView)

        init {
            petRecycler.layoutManager = GridLayoutManager(root.context, 2)
            petRecycler.adapter = petAdapter
        }
    }

    companion object {

        fun present(container: Container, pets: PagedList<PetListItemViewModel.Pet>) {
            container.petAdapter.submitList(pets)
        }

        fun presentError(container: Container, error: String) {
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