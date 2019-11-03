package com.chaseolson.pets.core

import com.chaseolson.pets.R
import com.chaseolson.pets.home.models.NewPhoto
import com.chaseolson.pets.oldstuff.Photo

fun String.filterName(): String {
    val nonNumberOrDollarSignRegex = Regex("[^A-Za-z &()*-]")
    return this.replace("&amp", "&").replace(nonNumberOrDollarSignRegex, "").trim()
}

fun String.animalToBackupImage() = when (this.toUpperCase()) {
    "CAT" -> R.drawable.cat_silhouette
    else -> R.drawable.dog_silhouette
}

fun String.charSizeToStringSize() = when (this.toUpperCase()) {
    "S" -> "Small"
    "M" -> "Medium"
    "L" -> "Large"
    "XL" -> "Extra Large"
    else -> "Size N/A"
}

fun String.charGenderToStringGender() = when (this.toUpperCase()) {
    "F" -> "Female"
    "M" -> "Male"
    else -> "Gender N/A"
}

fun String.genderAndLocationToString(city: String) = when (this.toLowerCase()) {
    "female" -> "She's in $city!"
    "male" -> "He's in $city!"
    else -> "They're in $city!"
}

//fun List<PetFinderResponse.Pet.Breed>.mapBreedsToList() = this.map { it.breed }

fun List<Photo>.filterImagesList() = this.filter { it.size == "pn" }.map { it.photo }
fun List<NewPhoto>.getSmallImage() = firstOrNull()?.small
fun List<NewPhoto>.getMediumImage() = firstOrNull()?.medium

fun String.animalToSearchQuery() = when (this) {
    "Dogs" -> "dog"
    "Cats" -> "cat"
    else -> null
}

fun String.sizeToSearchQuery() = when (this) {
    "Small" -> "S"
    "Medium" -> "M"
    "Large" -> "L"
    "Extra Large" -> "XL"
    else -> null
}

fun String.genderToSearchQuery() = when (this) {
    "Female" -> "F"
    "Male" -> "M"
    else -> null
}