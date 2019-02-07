package com.chaseolson.pets.home.presenter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetListItemViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pet_list_item.view.*

class PetRecyclerItemPresenter {
    class Container(root: ViewGroup) : RecyclerView.ViewHolder(root) {
        val name: TextView = root.pet_name
        val city: TextView = root.pet_city
        val age: TextView = root.pet_age
        val gender: TextView = root.pet_gender
        val size: TextView = root.pet_size
        val breedOne: TextView = root.pet_breed_one
        val breedTwo: TextView = root.pet_breed_two
        val image: ImageView = root.pet_image

        init {
            root.setOnClickListener { Toast.makeText(root.context, "Pet Details Coming Soon!", Toast.LENGTH_SHORT).show() }
        }
    }

    companion object {
        fun present(container: Container, vm: PetListItemViewModel.Pet) {
            container.run {
                name.text = vm.name
                city.text = vm.city
                age.text = vm.age
                gender.text = vm.gender
                size.text = vm.size
                breedOne.text = vm.breed[0]
                breedTwo.text = vm.breed.getOrNull(1)
                vm.images.getOrNull(0)?.run {
                    Picasso.get().load(this).error(vm.backupImage).into(image)
                } ?: run { Picasso.get().load(vm.backupImage).into(image) }
            }
        }
    }
}