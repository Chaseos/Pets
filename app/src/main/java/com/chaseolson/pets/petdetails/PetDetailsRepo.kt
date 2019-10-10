package com.chaseolson.pets.petdetails

import androidx.lifecycle.liveData
import com.chaseolson.pets.core.ResultWrapper
import com.chaseolson.pets.core.Status
import com.chaseolson.pets.core.filterImagesList
import com.chaseolson.pets.home.HomeScreenRepo.Companion.responseToViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetDetailsRepo(val listener: Listener) {
    interface Listener {
        fun present(vs: PetDetailsViewState)
    }

//    fun getPetDetailsData(id: Int) = liveData {
//        emit(ResultWrapper(Status.LOADING, null, null))
//        val call = PetDetailsApiImpl().getPet(id)
//        val response = call.execute()
//        when {
//            response.isSuccessful && response.body() == null -> emit(ResultWrapper(Status.ERROR, null, "Body null"))
//            response.isSuccessful -> emit(ResultWrapper(Status.SUCCESS, response.body(), null))
//            !response.isSuccessful -> emit(ResultWrapper(Status.ERROR, response.body(), response.message()))
//        }
//    }

//    suspend fun getPetDetailsData2(id: Int): PetDetailsResponse {
////        emit(ResultWrapper(Status.LOADING, null, null))
//        val callback = object : Callback<PetDetailsResponse> {
//            override fun onFailure(call: Call<PetDetailsResponse>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<PetDetailsResponse>, response: Response<PetDetailsResponse>) {
//                if (response.isSuccessful) {
////                    return responseToViewModel(response.body()?.pet)
//                }
//            }
//        }
//
//        val response = PetDetailsApiImpl().getPet(id).enqueue(object: Callback<PetDetailsResponse> {
//            override fun onFailure(call: Call<PetDetailsResponse>, t: Throwable) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onResponse(call: Call<PetDetailsResponse>, response: Response<PetDetailsResponse>) {
////                return response.body()
//            }
//
//        })
//
//    }

    companion object {
        fun responseToViewModel(petDetails: PetDetailsResponse.Pet?): PetDetailsViewState {
            return PetDetailsViewState(
//                id = petDetails?.id,
//                image = petDetails?.photos?.run { this.filterImagesList() } ?: emptyList()
            )
        }
    }
}