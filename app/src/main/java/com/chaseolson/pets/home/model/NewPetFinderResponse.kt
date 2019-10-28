package com.chaseolson.pets.home.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewPetFinderResponse(
    val animals: List<Animal>?,
    val pagination: Pagination?
) {
    @Serializable
    data class Animal(
        @SerialName("_links")
        val links: AnimalLinks?,
        val age: String?,
        val attributes: Attributes?,
        val breeds: Breeds?,
        val coat: String?,
        val colors: Colors?,
        val contact: Contact?,
        val description: String?,
        val distance: Double?,
        val environment: Environment?,
        val gender: String?,
        val id: Int?,
        val name: String?,
        @SerialName("organization_id")
        val organizationId: String?,
        val photos: List<Photo>?,
        @SerialName("published_at")
        val publishedAt: String?,
        val size: String?,
        val species: String?,
        val status: String?,
        val tags: List<String>?,
        val type: String?,
        val url: String?
    ) {
        @Serializable
        data class Attributes(
            val declawed: Boolean?,
            @SerialName("house_trained")
            val houseTrained: Boolean?,
            @SerialName("shots_current")
            val shotsCurrent: Boolean?,
            @SerialName("spayed_neutered")
            val spayedNeutered: Boolean?,
            @SerialName("special_needs")
            val specialNeeds: Boolean?
        )

        @Serializable
        data class Breeds(
            val mixed: Boolean?,
            val primary: String?,
            val secondary: String?,
            val unknown: Boolean?
        )

        @Serializable
        data class Colors(
            val primary: String?,
            val secondary: String?,
            val tertiary: String?
        )

        @Serializable
        data class Contact(
            val address: Address?,
            val email: String?,
            val phone: String?
        )

        @Serializable
        data class Environment(
            val cats: Boolean?,
            val children: Boolean?,
            val dogs: Boolean?
        )
    }

    @Serializable
    data class AnimalLinks(
        val organization: Organization?,
        val self: Self?,
        val type: Type?
    ) {
        @Serializable
        data class Organization(
            val href: String?
        )

        @Serializable
        data class Self(
            val href: String?
        )

        @Serializable
        data class Type(
            val href: String?
        )
    }

    @Serializable
    data class Pagination(
        @SerialName("_links")
        val links: AnimalLinks?,
        @SerialName("count_per_page")
        val countPerPage: Int?,
        @SerialName("current_page")
        val currentPage: Int?,
        @SerialName("total_count")
        val totalCount: Int?,
        @SerialName("total_pages")
        val totalPages: Int
    )
}