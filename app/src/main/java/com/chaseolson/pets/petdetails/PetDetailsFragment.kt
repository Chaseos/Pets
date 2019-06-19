package com.chaseolson.pets.petdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.chaseolson.pets.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetDetailsFragment: Fragment() {

    private val viewModel: PetDetailsViewModel by lazy { ViewModelProviders.of(this).get(PetDetailsViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            val callback = object: Callback<PetResponse> {
                override fun onFailure(call: Call<PetResponse>, t: Throwable) {

                }

                override fun onResponse(call: Call<PetResponse>, response: Response<PetResponse>) {

                }

            }
            val request = PetDetailsApiImpl().getPet(this.getInt("petId")).enqueue(callback)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pet_details_fragment, container, false)
    }
}