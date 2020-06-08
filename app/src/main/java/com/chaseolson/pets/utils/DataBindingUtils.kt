package com.chaseolson.pets.utils

import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.chaseolson.pets.details.PetDetailViewState
import com.chaseolson.pets.home.PetListViewState
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
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

@BindingAdapter("paletteImage")
fun ImageView.bindLoadImagePaletteView(images: PetDetailViewState.Images) {
    val parent = (parent.parent as View)
    Glide.with(context)
        .load(images.largeImage)
        .thumbnail(Glide.with(context).load(images.smallImage))
        .fallback(images.backupImage)
        .listener(
            GlidePalette.with(images.smallImage)
                .use(BitmapPalette.Profile.MUTED_LIGHT)
                .intoCallBack { palette ->
                    val dominant = palette?.dominantSwatch?.rgb
                    if (dominant != null) {
                        parent.setBackgroundColor(dominant)
                        (context as AppCompatActivity).window.apply {
                            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                            statusBarColor = dominant
                        }
                    }
                })
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