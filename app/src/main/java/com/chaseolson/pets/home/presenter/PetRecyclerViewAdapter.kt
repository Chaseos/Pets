package com.chaseolson.pets.home.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetListItemViewModel

class PetRecyclerViewAdapter :
    PagedListAdapter<PetListItemViewModel.Pet, RecyclerView.ViewHolder>(PET_COMPARATOR) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        PetRecyclerItemPresenter.present(holder as PetRecyclerItemPresenter.Container, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.pet_list_item, parent, false)
        val constraintLayout: ConstraintLayout = root.findViewById(R.id.pet_layout)
        return PetRecyclerItemPresenter.Container(constraintLayout)
    }

    companion object {
        val PET_COMPARATOR = object : DiffUtil.ItemCallback<PetListItemViewModel.Pet>() {
            override fun areItemsTheSame(
                oldItem: PetListItemViewModel.Pet, newItem: PetListItemViewModel.Pet
            ): Boolean = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: PetListItemViewModel.Pet, newItem: PetListItemViewModel.Pet
            ): Boolean = oldItem == newItem

        }
    }
}