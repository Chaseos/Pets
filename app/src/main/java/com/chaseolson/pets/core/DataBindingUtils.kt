package com.chaseolson.pets.core

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.chaseolson.pets.home.models.PetListItemViewState
import com.google.android.material.bottomnavigation.BottomNavigationView

@BindingAdapter("glidePet")
fun ImageView.loadGlideImage(pet: PetListItemViewState.Pet) {
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

@BindingAdapter("onNavigationItemSelected")
fun BottomNavigationView.setNavigationItemSelectedListener(listener: BottomNavigationView.OnNavigationItemSelectedListener) =
    setOnNavigationItemSelectedListener(listener)

@BindingAdapter("onRefreshListener")
fun SwipeRefreshLayout.setRefreshListener(listener: SwipeRefreshLayout.OnRefreshListener) =
    setOnRefreshListener(listener)