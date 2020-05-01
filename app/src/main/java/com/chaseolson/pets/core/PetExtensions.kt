package com.chaseolson.pets.core

import com.chaseolson.pets.R
import com.chaseolson.pets.home.models.Photo
import java.util.*

fun String.filterName(): String {
    val nonNumberOrDollarSignRegex = Regex("[^A-Za-z &()*-]")
    return this.replace("&amp", "&").replace(nonNumberOrDollarSignRegex, "").trim()
}

fun String.animalToBackupImage() = when (this.toUpperCase(Locale.getDefault())) {
    "CAT" -> R.drawable.cat_silhouette
    else -> R.drawable.dog_silhouette
}

fun String.charSizeToStringSize() = when (this.toUpperCase(Locale.getDefault())) {
    "S" -> "Small"
    "M" -> "Medium"
    "L" -> "Large"
    "XL" -> "Extra Large"
    else -> "Size N/A"
}

fun String.charGenderToStringGender() = when (this.toUpperCase(Locale.getDefault())) {
    "F" -> "Female"
    "M" -> "Male"
    else -> "Gender N/A"
}

//TODO if name has "and" in it, make it They
fun String.genderAndLocationToString(city: String, name: String): String {
    return when {
        name.contains(" and ") or name.contains(" & ") -> "They're in $city"
        toLowerCase(Locale.getDefault()) == "female" -> "She's in $city!"
        toLowerCase(Locale.getDefault()) == "male" -> "He's in $city!"
        else -> "They're in $city!"
    }
}

//fun List<OldPetFinderResponse.Pet.Breed>.mapBreedsToList() = this.map { it.breed }

//fun List<OldPhoto>.filterImagesList() = this.filter { it.size == "pn" }.map { it.photo }
fun List<Photo>.getSmallImage() = firstOrNull()?.small

fun List<Photo>.getMediumImage() = firstOrNull()?.medium

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

fun Double.getDistance() = String.format("%.1f", this) + "mi"