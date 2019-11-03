package com.chaseolson.pets.module

import kotlinx.serialization.Serializable

@Serializable
data class NewPhoto(
    val full: String,
    val large: String,
    val medium: String,
    val small: String
)