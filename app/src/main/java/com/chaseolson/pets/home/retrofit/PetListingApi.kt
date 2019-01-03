package com.chaseolson.pets.home.retrofit

import com.chaseolson.pets.home.model.PetFinder
import retrofit2.Call

interface PetListingApi {
    fun getPetsList(
        animal: String? = null,
        breed: String? = null,
        size: String? = null,
        sex: Char? = null,
        location: String,
        age: String? = null,
        offset: String? = null,
        count: Int? = null,
        output: String? = null,
        format: String? = null
    ): Call<PetFinder>
}