package com.chaseolson.pets.oldstuff

import com.chaseolson.pets.oldstuff.petdetails.PetDetailsResponse
import retrofit2.Call

interface PetDetailsAPI {
    fun getPet(id: Int): Call<PetDetailsResponse>
}

class PetDetailsApiImpl: PetDetailsAPI {
    override fun getPet(id: Int): Call<PetDetailsResponse> = RetrofitApi().getPet(id)
}