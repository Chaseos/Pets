package com.chaseolson.pets.core

import com.chaseolson.pets.BuildConfig
import com.chaseolson.pets.home.model.PetBreedsResponse
import com.chaseolson.pets.home.model.PetFinderResponse
import com.chaseolson.pets.petdetails.PetDetailsResponse
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

        val consumerKey = BuildConfig.petFinderKey2
        val consumerSecret = BuildConfig.petFinderSecret2

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

    suspend fun getToken(): Call<TokenResponseDto> {
        val key = BuildConfig.petFinderKey2
        val secret = BuildConfig.petFinderSecret2

        return mobileEndpoints2.getToken(key, secret)
    }

    suspend fun getPetListing2(
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

        return mobileEndpoints2.getPetListing2(key, animal, breed, size, sex, location, age, offset, count, output, format)
    }

    suspend fun getPetBreeds2(animal: String? = null): Call<PetBreedsResponse> {
        val key = BuildConfig.petFinderKey
        val format = "xml"

        return mobileEndpoints2.getPetBreeds2(key, animal, format)
    }

    suspend fun getPet2(id: Int): Call<PetDetailsResponse> {
        val key = BuildConfig.petFinderKey
        val format = "xml"

        return mobileEndpoints2.getPet2(key, id, format)
    }
}