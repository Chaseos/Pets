package com.chaseolson.pets.home.model

data class PetListItemViewModel(val pets: List<Pet>? = null){

    data class Pet(val name: String? = null,
                   val age: String? = null,
                   val gender: String? = null,
                   val breed: String? = null,
                   val images: List<String?>? = null)
}