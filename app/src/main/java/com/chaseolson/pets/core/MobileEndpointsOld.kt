package com.chaseolson.pets.core

import com.chaseolson.pets.home.model.PetBreedsResponse
import com.chaseolson.pets.home.model.PetFinderResponse
import com.chaseolson.pets.petdetails.PetDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MobileEndpointsOld {

    @GET("pet.find")
    fun getPetListing(
        @Query("key") key: String,
        @Query("animal") animal: String?,
        @Query("breed") breed: String?,
        @Query("size") size: String?,
        @Query("sex") sex: String?,
        @Query("location") location: String,
        @Query("age") age: String?,
        @Query("offset") offset: Int?,
        @Query("count") count: Int?,
        @Query("output") output: String?,
        @Query("format") format: String
    ): Call<PetFinderResponse>

    @GET("breed.list")
    fun getPetBreeds(
            @Query("key") key: String,
            @Query("animal") animal: String?,
            @Query("format") format: String
    ): Call<PetBreedsResponse>

    @GET("pet.get")
    fun getPet(
            @Query("key") key: String,
            @Query("id") id: Int,
            @Query("format") format: String
    ): Call<PetDetailsResponse>

    @GET("oauth2/token")
    suspend fun getToken(
        @Query("client_id") key: String,
        @Query("client_secret") secret: String
    ): Call<TokenResponseDto>

    @GET("pet.find")
    suspend fun getPetListing2(
        @Query("key") key: String,
        @Query("animal") animal: String?,
        @Query("breed") breed: String?,
        @Query("size") size: String?,
        @Query("sex") sex: String?,
        @Query("location") location: String,
        @Query("age") age: String?,
        @Query("offset") offset: Int?,
        @Query("count") count: Int?,
        @Query("output") output: String?,
        @Query("format") format: String
    ): Call<PetFinderResponse>

    @GET("breed.list")
    suspend fun getPetBreeds2(
            @Query("key") key: String,
            @Query("animal") animal: String?,
            @Query("format") format: String
    ): Call<PetBreedsResponse>

    @GET("pet.get")
    suspend fun getPet2(
            @Query("key") key: String,
            @Query("id") id: Int,
            @Query("format") format: String
    ): Call<PetDetailsResponse>
}