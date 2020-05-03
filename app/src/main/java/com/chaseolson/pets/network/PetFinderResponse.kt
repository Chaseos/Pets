package com.chaseolson.pets.network

import com.chaseolson.pets.details.PetDetailViewState
import com.chaseolson.pets.home.PetListViewState
import com.chaseolson.pets.home.PetListViewState.Pet.Images
import com.chaseolson.pets.utils.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PetFinderResponse(
    val animals: List<Animal>? = null,
    val pagination: Pagination? = null
) {
    @Serializable
    data class Animal(
        val id: Int,
        val age: String? = null,
        val attributes: Attributes? = null,
        val breeds: Breeds? = null,
        val coat: String? = null,
        val colors: Colors? = null,
        val contact: Contact = Contact(),
        val description: String? = null,
        val distance: Double? = null,
        val environment: Environment? = null,
        val gender: String? = null,
        val name: String? = null,
        @SerialName("organization_id")
        val organizationId: String? = null,
        val photos: List<Photo> = emptyList(),
        @SerialName("primary_photo_cropped")
        val primaryPhotoCropped: Photo? = null,
        @SerialName("published_at")
        val publishedAt: String? = null,
        val size: String? = null,
        val species: String? = null,
        val status: String? = null,
        @SerialName("status_changed_at")
        val statusChangedAt: String? = null,
        val tags: List<String>? = null,
        val type: String,
        val url: String? = null,
        @SerialName("_links")
        val links: AnimalLinks? = null
//        val videos: List<Any> = emptyList() Don't know type as all I get is empty list
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
        data class Address(
            val address1: String? = null,
            val address2: String? = null,
            val city: String = "N/A",
            val country: String? = null,
            val postcode: String? = null,
            val state: String? = null
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
            val address: Address = Address(),
            val email: String? = null,
            val phone: String? = null
        )

        @Serializable
        data class Environment(
            val cats: Boolean? = null,
            val children: Boolean? = null,
            val dogs: Boolean? = null
        )

        @Serializable
        data class Photo(
            val full: String,
            val large: String,
            val medium: String,
            val small: String
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

fun PetFinderResponse.responseToViewState(): PetListViewState? {
    val newList = animals?.distinctBy { it.id }?.map { pet ->
        val petName = pet.name?.filterName() ?: "No Name"
        PetListViewState.Pet(
            id = pet.id,
            name = petName,
            city = pet.gender?.genderAndLocationToString(pet.contact.address.city, petName),
            distance = pet.distance?.getDistance(),
            images = Images(
                smallImage = pet.primaryPhotoCropped?.small,
                mediumImage = pet.primaryPhotoCropped?.medium,
                backupImage = pet.type.animalToBackupImage()
            ),
            offset = pagination?.currentPage ?: 0
        )
    }

    return PetListViewState(newList)
}

//TODO Update ViewState with states relative to the comps we end up using
fun PetFinderResponse.Animal.responseToDetailViewState(): PetDetailViewState? {
    val petName = name?.filterName() ?: "No Name"
    return PetDetailViewState(
        id = id,
        name = petName,
        age = age ?: "N/A",
        attributes = attributes,
        breeds = breeds,
        coat = coat,
        colors = colors,
        contact = contact,
        description = description,
        distance = distance?.getDistance(),
        environment = environment,
        gender = gender,
        organizationId = organizationId,
        images = PetDetailViewState.Images(
            smallImage = photos.getSmallImage(),
            largeImage = photos.getLargeImage(),
            backupImage = type.animalToBackupImage()
        ),
        publishedAt = publishedAt,
        size = size,
        species = species,
        status = status,
        statusChangedAt = statusChangedAt,
        tags = tags,
        type = type
    )
}
