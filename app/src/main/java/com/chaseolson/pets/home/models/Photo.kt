package com.chaseolson.pets.home.models

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val full: String,
    val large: String,
    val medium: String,
    val small: String
)