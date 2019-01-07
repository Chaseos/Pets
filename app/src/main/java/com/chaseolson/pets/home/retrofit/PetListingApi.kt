package com.chaseolson.pets.home.retrofit

import com.chaseolson.pets.core.RetrofitApi
import com.chaseolson.pets.home.model.PetFinderResponse
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
    ): Call<PetFinderResponse>
}

class PetListingApiImpl: PetListingApi {

    override fun getPetsList(
        animal: String?,
        breed: String?,
        size: String?,
        sex: Char?,
        location: String,
        age: String?,
        offset: String?,
        count: Int?,
        output: String?,
        format: String?
    ): Call<PetFinderResponse> {
        return RetrofitApi().getPetListing(animal, breed, size, sex, location, age, offset, count, output, format)
    }
}