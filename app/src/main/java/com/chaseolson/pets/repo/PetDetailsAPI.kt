package com.chaseolson.pets.repo

import com.chaseolson.pets.core.RetrofitApi
import com.chaseolson.pets.petdetails.PetDetailsResponse
import retrofit2.Call

interface PetDetailsAPI {
    fun getPet(id: Int): Call<PetDetailsResponse>
}

class PetDetailsApiImpl: PetDetailsAPI {
    override fun getPet(id: Int): Call<PetDetailsResponse> = RetrofitApi().getPet(id)
}