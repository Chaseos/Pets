package com.chaseolson.pets.oldstuff

data class OldPetListItemViewState(val pets: List<Pet>? = null) {

    data class Pet(
        val id: Int = 0,
        val name: String = "N/A",
        val age: String = "N/A",
        val gender: String = "N/A",
        val size: String = "N/A",
        val breed: List<String> = listOf("N/A"),
        val images: List<String> = emptyList(),
        val backupImage: Int = 0,
        val city: String? = null,
        val offset: Int? = 0
    )
}