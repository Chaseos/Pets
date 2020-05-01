package com.chaseolson.pets.home.models

data class PetListViewState(val pets: List<Pet>? = null) {

    //TODO Figure out best way to handle transitioning this info to Detail view
    // Possible: Activity's ViewModel or with safeargs
    data class Pet(
        val id: Int = 0,
        val name: String = "N/A",
        val age: String = "N/A",
        val gender: String = "N/A",
        val size: String = "N/A",
        val breed: List<String> = listOf("N/A"),
        val city: String? = null,
        val distance: String?,
        val images: Images,
        val offset: Int? = 0
    ) {
        data class Images(
            val smallImage: String? = null,
            val mediumImage: String? = null,
            val backupImage: Int = 0
        )
    }
}