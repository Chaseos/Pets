package com.chaseolson.pets.home.models

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val address1: String? = null,
    val address2: String? = null,
    val city: String = "N/A",
    val country: String? = null,
    val postcode: String? = null,
    val state: String? = null
)