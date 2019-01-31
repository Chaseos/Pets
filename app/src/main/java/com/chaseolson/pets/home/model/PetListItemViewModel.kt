package com.chaseolson.pets.home.model

data class PetListItemViewModel(val pets: List<Pet>? = null) {

    data class Pet(
        val name: String = "Unknown Name",
        val age: String = "Unknown Age",
        val gender: String = "Unknown Gender",
        val breed: List<String> = listOf("Unknown Breed"),
        val images: List<String> = emptyList(),
        val backupImage: Int = 0,
        val offset: Int? = 0
    )
}