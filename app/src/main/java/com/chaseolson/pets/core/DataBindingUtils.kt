package com.chaseolson.pets.core

import android.R
import android.widget.ImageView
import com.squareup.picasso.Picasso
import androidx.databinding.BindingAdapter

class DataBindingUtils {

    @BindingAdapter("android:imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        Picasso.get().load(imageUrl).error(com.chaseolson.pets.R.drawable.cat_silhouette).into(view)
    }
}