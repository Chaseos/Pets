package com.chaseolson.pets.core

import com.chaseolson.pets.home.model.PetFinderResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MobileEndpoints {

    @GET("pet.find")
    fun getPetListing(
        @Query("key") key: String,
        @Query("animal") animal: String?,
        @Query("breed") breed: String?,
        @Query("size") size: String?,
        @Query("sex") sex: Char?,
        @Query("location") location: String,
        @Query("age") age: String?,
        @Query("offset") offset: Int?,
        @Query("count") count: Int?,
        @Query("output") output: String?,
        @Query("format") format: String?
    ): Call<PetFinderResponse>
}