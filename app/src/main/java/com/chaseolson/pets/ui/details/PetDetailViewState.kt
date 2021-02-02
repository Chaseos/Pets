package com.chaseolson.pets.ui.details

import com.chaseolson.pets.network.PetFinderResponse.Animal

data class PetDetailViewState(
    val id: Int = 0,
    val name: String = "N/A",
    val age: String = "N/A",
    val attributes: Animal.Attributes? = null,
    val breeds: Animal.Breeds? = null,
    val coat: String? = null,
    val colors: Animal.Colors? = null,
    val contact: Animal.Contact? = null,
    val description: String? = null,
    val distance: String?,
    val environment: Animal.Environment? = null,
    val gender: String? = "N/A",
    val organizationId: String? = null,
    val images: Images,
    val publishedAt: String? = null,
    val size: String? = "N/A",
    val species: String? = null,
    val status: String? = null,
    val statusChangedAt: String? = null,
    val tags: List<String>? = null,
    val type: String
) {

    //TODO Make these cool UI instead of this
    data class Attributes(
        val declawed: Boolean? = null,
        val houseTrained: Boolean? = null,
        val shotsCurrent: Boolean? = null,
        val spayedNeutered: Boolean? = null,
        val specialNeeds: Boolean? = null
    )

    data class Breeds(
        val mixed: Boolean? = null,
        val primary: String? = null,
        val secondary: String? = null,
        val unknown: Boolean? = null
    )

    data class Colors(
        val primary: String? = null,
        val secondary: String? = null,
        val tertiary: String? = null
    )

    data class Contact(
        val address: Animal.Address = Animal.Address(),
        val email: String? = null,
        val phone: String? = null
    )

    data class Environment(
        val cats: Boolean? = null,
        val children: Boolean? = null,
        val dogs: Boolean? = null
    )

    data class Images(
        val smallImage: String? = null,
        val largeImage: String? = null,
        val backupImage: Int = 0
    )
}