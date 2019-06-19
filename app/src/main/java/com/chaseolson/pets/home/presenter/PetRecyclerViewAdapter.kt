package com.chaseolson.pets.home.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetListItemViewState
import kotlinx.android.synthetic.main.pet_list_item.view.*

class PetRecyclerViewAdapter(val listener: PetRecyclerItemPresenter.Listener) :
    PagedListAdapter<PetListItemViewState.Pet, RecyclerView.ViewHolder>(PET_COMPARATOR) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        PetRecyclerItemPresenter(listener).present(holder as PetRecyclerItemPresenter.Container, getItem(position) ?: PetListItemViewState.Pet())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.pet_list_item, parent, false)
        val cardViewLayout: CardView = root.pet_layout
        return PetRecyclerItemPresenter.Container(cardViewLayout)
    }

    companion object {
        val PET_COMPARATOR = object : DiffUtil.ItemCallback<PetListItemViewState.Pet>() {
            override fun areItemsTheSame(
                    oldItem: PetListItemViewState.Pet, newItem: PetListItemViewState.Pet
            ): Boolean = oldItem.name == newItem.name && oldItem.id == newItem.id

            override fun areContentsTheSame(
                    oldItem: PetListItemViewState.Pet, newItem: PetListItemViewState.Pet
            ): Boolean = oldItem == newItem

        }
    }
}