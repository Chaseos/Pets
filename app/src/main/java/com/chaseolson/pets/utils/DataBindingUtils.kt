package com.chaseolson.pets.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.chaseolson.pets.details.PetDetailViewState
import com.chaseolson.pets.home.PetListViewState
import com.google.android.material.bottomnavigation.BottomNavigationView

@BindingAdapter("glidePet")
fun ImageView.loadGlideImage(images: PetListViewState.Pet.Images) {
    Glide.with(context)
        .load(images.mediumImage)
        .centerCrop()
        .thumbnail(Glide.with(context).load(images.smallImage))
        .fallback(images.backupImage)
        .into(this)
}

@BindingAdapter("glidePet")
fun ImageView.loadGlideImage(images: PetDetailViewState.Images) {
    Glide.with(context)
        .load(images.largeImage)
        .centerCrop()
        .thumbnail(Glide.with(context).load(images.smallImage))
        .fallback(images.backupImage)
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