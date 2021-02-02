package com.chaseolson.pets.ui.utils

import com.chaseolson.pets.R
import com.chaseolson.pets.network.PetFinderResponse.Animal.Photo
import java.util.*

fun String.filterName(): String {
    val nonNumberOrDollarSignRegex = Regex("[^A-Za-z &()*-]")
    return replace("&amp", "&")
        .replace(nonNumberOrDollarSignRegex, "")
        .trim()
}

fun String.animalToBackupImage() = when (this.toUpperCase(Locale.getDefault())) {
    "CAT" -> R.drawable.cat_silhouette
    else -> R.drawable.dog_silhouette
}

fun String.genderAndLocationToString(city: String, name: String): String {
    return when {
        name.contains(" and ") or name.contains(" & ") -> "They're in $city"
        toLowerCase(Locale.getDefault()) == "female" -> "She's in $city!"
        toLowerCase(Locale.getDefault()) == "male" -> "He's in $city!"
        else -> "They're in $city!"
    }
}

fun List<Photo>.getSmallImage() = firstOrNull()?.small

fun List<Photo>.getLargeImage() = firstOrNull()?.large

fun List<Photo>.getFullImage() = firstOrNull()?.full

fun String.animalToSearchQuery() = when (this) {
    "Dogs" -> "Dog"
    "Cats" -> "Cat"
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