package com.chaseolson.pets.repo

import com.chaseolson.pets.core.RetrofitApi
import com.chaseolson.pets.petdetails.PetDetailsResponse
import retrofit2.Call

interface PetDetailsAPI2 {
    suspend fun getPet(id: Int): Call<PetDetailsResponse>
}

class PetDetailsApiImpl2(private val retrofitApi: RetrofitApi): PetDetailsAPI2 {
    override suspend fun getPet(id: Int): Call<PetDetailsResponse> = retrofitApi.getPet(id)
}