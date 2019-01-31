package com.chaseolson.pets.home.presenter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetListItemViewModel
import com.squareup.picasso.Picasso

class PetRecyclerItemPresenter {
    class Container(root: ViewGroup) : RecyclerView.ViewHolder(root) {
        val name: TextView = root.findViewById(R.id.pet_name)
        val age: TextView = root.findViewById(R.id.pet_age)
        val gender: TextView = root.findViewById(R.id.pet_gender)
        val breedOne: TextView = root.findViewById(R.id.pet_breed_one)
        val breedTwo: TextView = root.findViewById(R.id.pet_breed_two)
        val image: ImageView = root.findViewById(R.id.pet_image)

        init {
            root.setOnClickListener {}
        }
    }

    companion object {
        fun present(container: Container, vm: PetListItemViewModel.Pet) {
            container.run {
                name.text = vm.name
                age.text = vm.age
                gender.text = vm.gender
                breedOne.text = vm.breed[0]
                breedTwo.text = vm.breed.getOrNull(1)
                vm.images.getOrNull(0)?.run {
                    Picasso.get().load(this).error(vm.backupImage).into(image)
                } ?: run { Picasso.get().load(vm.backupImage).into(image) }
            }
        }
    }
}