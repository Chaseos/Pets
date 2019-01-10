package com.chaseolson.pets.home.presenter

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetListItemViewModel

class HomeScreenPresenter(private val root: View) {
    private val petRecycler: RecyclerView = root.findViewById(R.id.pet_recyclerView)

    init {
        petRecycler.layoutManager = GridLayoutManager(root.context, 2)
    }

    fun present(vm: PetListItemViewModel){
        petRecycler.adapter = PetRecyclerViewAdapter(vm.pets)
    }

    fun presentError(error: String) {
        val dialog = AlertDialog.Builder(root.context)
            .setTitle(error)
            .setPositiveButton("Ok") { dialog, which ->
                //TODO
            }
            .create()
        dialog.show()
    }
}