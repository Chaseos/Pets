package com.chaseolson.pets.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PetTypesResponse(
    val types: List<Type>
) {
    @Serializable
    data class Type(
        @SerialName("_links")
        val links: Links,
        val coats: List<String>,
        val colors: List<String>,
        val genders: List<String>,
        val name: String
    )
    {
        @Serializable
        data class Links(
            val breeds: Breeds,
            val self: Self
        ) {
            @Serializable
            data class Breeds(
                val href: String
            )

            @Serializable
            data class Self(
                val href: String
            )
        }
    }
}