package com.chaseolson.pets.core

import android.view.View
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

@BindingAdapter("isVisible")
fun View.setVisibility(value: Boolean?) {
    visibility = if (value == true) View.VISIBLE else View.GONE
}