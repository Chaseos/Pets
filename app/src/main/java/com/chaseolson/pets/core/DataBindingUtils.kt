package com.chaseolson.pets.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.chaseolson.pets.home.model.NewPetListItemViewState

@BindingAdapter("glidePet")
fun ImageView.loadGlideImage(pet: NewPetListItemViewState.NewPet) {
    Glide.with(context)
            .load(pet.mediumImage)
            .centerCrop()
            .thumbnail(Glide.with(context).load(pet.smallImage))
            .fallback(pet.backupImage)
            .into(this)
}