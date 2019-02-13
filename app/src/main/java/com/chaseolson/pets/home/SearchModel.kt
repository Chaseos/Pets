package com.chaseolson.pets.home

data class SearchModel(
        val animal: String? = null,
        val breed: String? = null,
        val size: String? = null,
        val sex: String? = null,
        val location: String? = null,
        val age: String? = null)