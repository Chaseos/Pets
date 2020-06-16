package com.chaseolson.pets.network

import kotlinx.serialization.Serializable

@Serializable
data class PetBreedsResponse(
    val breeds: List<Breed>
) {
    @Serializable
    data class Breed(
        val _links: Links,
        val name: String
    ) {
        @Serializable
        data class Links(
            val type: Type
        ) {
            @Serializable
            data class Type(
                val href: String
            )
        }
    }
}