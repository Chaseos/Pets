package com.chaseolson.pets.home.retrofit

import com.chaseolson.pets.core.RetrofitApi
import com.chaseolson.pets.home.model.PetFinder
import retrofit2.Call

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
    ): Call<PetFinder> {
        return RetrofitApi().getPetListing(animal, breed, size, sex, location, age, offset, count, output, format)
    }
}