package com.chaseolson.pets.core

import com.chaseolson.pets.R

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

fun String.genderAndLocationToString(city: String) = when (this.toUpperCase()) {
    "F" -> "She's in $city!"
    "M" -> "He's in $city!"
    else -> "They're in $city!"
}

//fun List<PetFinderResponse.Pet.Breed>.mapBreedsToList() = this.map { it.breed }

fun List<Photo>.filterImagesList() = this.filter { it.size == "pn" }.map { it.photo }

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