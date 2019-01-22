package com.chaseolson.pets.home.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetListItemViewModel

class PetRecyclerViewAdapter(private val petsList: List<PetListItemViewModel.Pet>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        PetRecyclerItemPresenter.present(holder as PetRecyclerItemPresenter.Container, petsList?.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.pet_list_item, parent, false)
        val constraintLayout: ConstraintLayout = root.findViewById(R.id.pet_layout)
        return PetRecyclerItemPresenter.Container(constraintLayout)
    }

    override fun getItemCount(): Int = petsList?.size ?: 0
}