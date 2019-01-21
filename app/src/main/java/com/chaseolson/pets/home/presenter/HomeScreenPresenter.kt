package com.chaseolson.pets.home.presenter

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetListItemViewModel

class HomeScreenPresenter() {

    class Container(val root: View, val listener: PetRecyclerItemPresenter.Listener) {

        val petRecycler: RecyclerView = root.findViewById(R.id.pet_recyclerView)

        init {
            petRecycler.layoutManager = GridLayoutManager(root.context, 2)
        }

    }

    companion object {

        fun present(container: Container, vm: PetListItemViewModel){
            container.petRecycler.adapter = PetRecyclerViewAdapter(vm.pets, container.listener)
        }

        fun presentError(container: Container, error: String) {
            val dialog = AlertDialog.Builder(container.root.context)
                .setTitle(error)
                .setPositiveButton("Ok") { dialog, which ->
                    //TODO
                }
                .create()
            dialog.show()
        }

    }
}