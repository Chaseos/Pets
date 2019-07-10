package com.chaseolson.pets.home.presenter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.chaseolson.pets.home.model.PetListItemViewState
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pet_list_item.view.*

class PetRecyclerItemPresenter(val listener: Listener) {
    interface Listener {
        fun petOnClick(id: Int, petImage: View)
    }

    class Container(val root: ViewGroup) : RecyclerView.ViewHolder(root) {
        val name: TextView = root.pet_name
        val city: TextView = root.pet_city
        val image: ImageView = root.pet_image

        init {
//            root.setOnClickListener { Toast.makeText(root.context, "Pet Details Coming Soon!", Toast.LENGTH_SHORT).show() }
        }
    }

    fun present(container: Container, vs: PetListItemViewState.Pet) {
        container.run {
            name.text = vs.name
            city.text = vs.city
            vs.images.getOrNull(0)?.run {
                Picasso.get().load(this).error(vs.backupImage).into(image)
            } ?: run { Picasso.get().load(vs.backupImage).into(image) }

            root.setOnClickListener {
                listener.petOnClick(vs.id, image)
                Toast.makeText(root.context, "Pet Details New Click!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}