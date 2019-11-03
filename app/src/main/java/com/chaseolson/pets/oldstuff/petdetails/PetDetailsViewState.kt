package com.chaseolson.pets.oldstuff.petdetails

import com.chaseolson.pets.R

data class PetDetailsViewState(
        val id: Int? = null,
        val image: String? = null,
        val backupImage: Int = R.drawable.cat_silhouette,
        val petName: String = "N/A"
)