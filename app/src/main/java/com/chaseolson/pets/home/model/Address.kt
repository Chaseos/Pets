package com.chaseolson.pets.home.model

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val address1: String?,
    val address2: String?,
    val city: String?,
    val country: String?,
    val postcode: String?,
    val state: String?
)