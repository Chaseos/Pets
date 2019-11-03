package com.chaseolson.pets.oldstuff

import com.chaseolson.pets.BuildConfig
import com.chaseolson.pets.oldstuff.petdetails.PetDetailsResponse
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit


class RetrofitApi {
    private var mobileEndpoints: MobileEndpointsOld
    private var mobileEndpoints2: MobileEndpointsOld

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

        val retrofit2 = Retrofit.Builder()
            .baseUrl("http://api.petfinder.com/v2/")
            .client(httpClient.build())
            .build()

        mobileEndpoints = retrofit.create(MobileEndpointsOld::class.java)
        mobileEndpoints2 = retrofit2.create(MobileEndpointsOld::class.java)
    }

    fun getPetListing(
        animal: String? = null,
        breed: String? = null,
        size: String? = null,
        sex: String? = null,
        location: String,
        age: String? = null,
        offset: Int? = null,
        count: Int? = null
    ): Call<PetFinderResponse> {

        val key = BuildConfig.petFinderKey
        val output = "basic"
        val format = "xml"

        return mobileEndpoints.getPetListing(key, animal, breed, size, sex, location, age, offset, count, output, format)
    }

    fun getPetBreeds(animal: String? = null): Call<PetBreedsResponse> {
        val key = BuildConfig.petFinderKey
        val format = "xml"

        return mobileEndpoints.getPetBreeds(key, animal, format)
    }

    fun getPet(id: Int): Call<PetDetailsResponse> {
        val key = BuildConfig.petFinderKey
        val format = "xml"

        return mobileEndpoints.getPet(key, id, format)
    }
}