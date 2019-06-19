package com.chaseolson.pets.petdetails

import com.chaseolson.pets.core.RetrofitApi
import retrofit2.Call

interface PetDetailsAPI {
    fun getPet(id: Int): Call<PetResponse>
}

class PetDetailsApiImpl: PetDetailsAPI {
    override fun getPet(id: Int): Call<PetResponse> = RetrofitApi().getPet(id)
}