package com.chaseolson.pets.core

import com.chaseolson.pets.BuildConfig
import com.chaseolson.pets.home.model.PetFinderResponse
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit


class RetrofitApi {
    private var mobileEndpoints: MobileEndpoints

    init {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val request = chain.request().newBuilder().addHeader("Connection", "close").build()
            chain.proceed(request)
        }

        val tik = TikXml.Builder()
            .exceptionOnUnreadXml(false)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.petfinder.com/")
            .client(httpClient.build())
            .addConverterFactory(TikXmlConverterFactory.create(tik))
            .build()

        mobileEndpoints = retrofit.create(MobileEndpoints::class.java)
    }

    fun getPetListing(
        animal: String? = null,
        breed: String? = null,
        size: String? = null,
        sex: Char? = null,
        location: String,
        age: String? = null,
        offset: Int? = null,
        count: Int? = null
    ): Call<PetFinderResponse> {

        val key = BuildConfig.petFinderKey
        val output = "basic"
        val format = "xml"

        return mobileEndpoints.getPetListing(
            key,
            animal,
            breed,
            size,
            sex,
            location,
            age,
            offset,
            count,
            output,
            format
        )
    }
}