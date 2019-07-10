package com.chaseolson.pets.petdetails

data class PetDetailsViewState(
        val id: Int? = null,
        val images: List<String> = emptyList()
)