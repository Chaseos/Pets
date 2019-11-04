package com.chaseolson.pets.oldstuff

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chaseolson.pets.R
import kotlinx.android.synthetic.main.pet_list_item.view.*

class PetRecyclerViewAdapter(val listener: PetRecyclerItemPresenter.Listener) :
    PagedListAdapter<OldPetListItemViewState.Pet, RecyclerView.ViewHolder>(PET_COMPARATOR) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        PetRecyclerItemPresenter(listener)
            .present(holder as PetRecyclerItemPresenter.Container, getItem(position) ?: OldPetListItemViewState.Pet())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.pet_list_item, parent, false)
        val cardViewLayout: CardView = root.pet_layout
        return PetRecyclerItemPresenter.Container(cardViewLayout)
    }

    companion object {
        val PET_COMPARATOR = object : DiffUtil.ItemCallback<OldPetListItemViewState.Pet>() {
            override fun areItemsTheSame(
                oldItem: OldPetListItemViewState.Pet, newItem: OldPetListItemViewState.Pet
            ): Boolean = oldItem.name == newItem.name && oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: OldPetListItemViewState.Pet, newItem: OldPetListItemViewState.Pet
            ): Boolean = oldItem == newItem

        }
    }
}