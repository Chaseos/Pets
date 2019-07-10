package com.chaseolson.pets.petdetails

import com.chaseolson.pets.core.filterImagesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetDetailsRepo(val listener: Listener) {
    interface Listener {
        fun present(vs: PetDetailsViewState)
    }

    fun setup(id: Int) {
        val callback = object : Callback<PetDetailsResponse> {
            override fun onFailure(call: Call<PetDetailsResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<PetDetailsResponse>, response: Response<PetDetailsResponse>) {
                if (response.isSuccessful) {
                    listener.present(responseToViewModel(response.body()?.pet))
                }
            }

        }
        val call = PetDetailsApiImpl().getPet(id)
        call.enqueue(callback)
    }

    companion object {
        fun responseToViewModel(petDetails: PetDetailsResponse.Pet?): PetDetailsViewState {
            return PetDetailsViewState(
                        id = petDetails?.id,
                        images = petDetails?.photos?.run { this.filterImagesList() } ?: emptyList()
                )
        }
    }
}