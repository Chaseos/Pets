package com.chaseolson.pets.home.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewPetFinderResponse(
    val animals: List<Animal>? = null,
    val pagination: Pagination? = null
) {
    @Serializable
    data class Animal(
        @SerialName("_links")
        val links: AnimalLinks? = null,
        val age: String? = null,
        val attributes: Attributes? = null,
        val breeds: Breeds? = null,
        val coat: String? = null,
        val colors: Colors? = null,
        val contact: Contact? = null,
        val description: String? = null,
        val distance: Double? = null,
        val environment: Environment? = null,
        val gender: String? = null,
        val id: Int? = null,
        val name: String? = null,
        @SerialName("organization_id")
        val organizationId: String? = null,
        val photos: List<Photo>? = null,
        @SerialName("published_at")
        val publishedAt: String? = null,
        val size: String? = null,
        val species: String? = null,
        val status: String? = null,
        val tags: List<String>? = null,
        val type: String? = null,
        val url: String? = null
    ) {
        @Serializable
        data class Attributes(
            val declawed: Boolean? = null,
            @SerialName("house_trained")
            val houseTrained: Boolean? = null,
            @SerialName("shots_current")
            val shotsCurrent: Boolean? = null,
            @SerialName("spayed_neutered")
            val spayedNeutered: Boolean? = null,
            @SerialName("special_needs")
            val specialNeeds: Boolean? = null
        )

        @Serializable
        data class Breeds(
            val mixed: Boolean? = null,
            val primary: String? = null,
            val secondary: String? = null,
            val unknown: Boolean? = null
        )

        @Serializable
        data class Colors(
            val primary: String? = null,
            val secondary: String? = null,
            val tertiary: String? = null
        )

        @Serializable
        data class Contact(
            val address: Address? = null,
            val email: String? = null,
            val phone: String? = null
        )

        @Serializable
        data class Environment(
            val cats: Boolean? = null,
            val children: Boolean? = null,
            val dogs: Boolean? = null
        )
    }

    @Serializable
    data class AnimalLinks(
        val organization: Organization? = null,
        val self: Self? = null,
        val type: Type? = null
    ) {
        @Serializable
        data class Organization(
            val href: String? = null
        )

        @Serializable
        data class Self(
            val href: String? = null
        )

        @Serializable
        data class Type(
            val href: String? = null
        )
    }

    @Serializable
    data class Pagination(
        @SerialName("_links")
        val links: AnimalLinks? = null,
        @SerialName("count_per_page")
        val countPerPage: Int? = null,
        @SerialName("current_page")
        val currentPage: Int? = null,
        @SerialName("total_count")
        val totalCount: Int? = null,
        @SerialName("total_pages")
        val totalPages: Int
    )
}