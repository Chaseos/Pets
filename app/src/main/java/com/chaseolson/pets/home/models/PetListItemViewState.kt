package com.chaseolson.pets.home.models

data class PetListItemViewState(val pets: List<Pet>? = null) {

    //TODO Put images into their own data class
    //TODO Parcelize. Don't use activity viewmodel as an object.
    data class Pet(
        val id: Int = 0,
        val name: String = "N/A",
        val age: String = "N/A",
        val gender: String = "N/A",
        val size: String = "N/A",
        val breed: List<String> = listOf("N/A"),
        val smallImage: String? = null,
        val mediumImage: String? = null,
        val backupImage: Int = 0,
        val city: String? = null,
        val distance: String?,
        val offset: Int? = 0
    )
}