package com.chaseolson.pets.core

import com.chaseolson.pets.R
import com.chaseolson.pets.home.model.PetFinderResponse

fun String.filterName(): String {
    val nonNumberOrDollarSignRegex = Regex("[^A-Za-z &()*-]")
    return this.replace(nonNumberOrDollarSignRegex, "").trim()
}

fun String.animalToBackupImage() = when (this.toUpperCase()) {
    "CAT" -> R.drawable.cat_silhouette
    else -> R.drawable.dog_silhouette
}

fun String.charSizeToStringSize() = when (this.toUpperCase()) {
    "S" -> "Small"
    "M" -> "Medium"
    "L" -> "Large"
    else -> "Size N/A"
}

fun String.charGenderToStringGender() = when (this.toUpperCase()) {
    "F" -> "Female"
    "M" -> "Male"
    else -> "Gender N/A"
}

fun List<PetFinderResponse.Pet.Breed>.mapBreedsToList() = this.map { it.breed }

fun List<PetFinderResponse.Pet.Photo>.filterImagesList() = this.filter { it.size == "pn" }.map { it.photo }