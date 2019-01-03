package com.chaseolson.pets.home.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetItemViewModel
import com.squareup.picasso.Picasso

class PetRecyclerViewAdapter(val petsList: List<PetItemViewModel.Pet>?) :
    RecyclerView.Adapter<PetRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(layout: ConstraintLayout) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetRecyclerViewAdapter.ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.pet_list_item, parent, false)
        val constraintLayout: ConstraintLayout = root.findViewById(R.id.pet_layout)
        return ViewHolder(constraintLayout)
    }

    override fun getItemCount(): Int {
        return petsList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pet = petsList?.get(position)

        PetRecyclerViewAdapter.present(holder, pet)
    }

    companion object {
        fun present(holder: ViewHolder, pet: PetItemViewModel.Pet?) {
            val name: TextView = holder.itemView.findViewById(R.id.pet_name)
            val age: TextView = holder.itemView.findViewById(R.id.pet_age)
            val gender: TextView = holder.itemView.findViewById(R.id.pet_gender)
            val breed: TextView = holder.itemView.findViewById(R.id.pet_breed)
//            val distance: TextView = holder.itemView.findViewById(R.id.pet_distance)
//            val shelter: TextView = holder.itemView.findViewById(R.id.pet_shelter)
            val image: ImageView = holder.itemView.findViewById(R.id.pet_image)

            name.text = pet?.name
            age.text = pet?.age
            gender.text = pet?.gender
            breed.text = pet?.breed
            Picasso.get().load(pet?.images?.get(0)).into(image)
        }
    }
}