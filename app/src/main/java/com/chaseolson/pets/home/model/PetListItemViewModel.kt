package com.chaseolson.pets.home.model

data class PetListItemViewModel(val pets: List<Pet>? = null){

    data class Pet(val name: String,
                   val age: String,
                   val gender: String,
                   val breed: String,
                   val images: List<String>)
}