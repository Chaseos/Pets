package com.chaseolson.pets.newhome

import android.graphics.drawable.Drawable
import com.chaseolson.pets.R

data class PetDetailsViewState2(
        val id: Int? = null,
        val smallImage: String? = null,
        val mediumImage: String? = null,
        val backupImage: Int = R.drawable.cat_silhouette,
        val petName: String = "N/A"
)