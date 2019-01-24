package com.chaseolson.pets.home.presenter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetListItemViewModel
import com.squareup.picasso.Picasso

class PetRecyclerItemPresenter {
    class Container(root: ViewGroup) : RecyclerView.ViewHolder(root) {
        val name: TextView = root.findViewById(R.id.pet_name)
        val age: TextView = root.findViewById(R.id.pet_age)
        val gender: TextView = root.findViewById(R.id.pet_gender)
        val breed: TextView = root.findViewById(R.id.pet_breed)
        val image: ImageView = root.findViewById(R.id.pet_image)

        init {
            root.setOnClickListener {
//                root.findNavController().navigate(R.id.petDetails)
            }
        }
    }

    companion object {
        fun present(container: Container, vm: PetListItemViewModel.Pet?) {
            container.run {
                name.text = vm?.name
                age.text = vm?.age
                gender.text = vm?.gender
                breed.text = vm?.breed
                Picasso.get().load(vm?.images?.get(0)).into(image)
            }
        }
    }
}