package com.chaseolson.pets.repo

import com.chaseolson.pets.BuildConfig
import com.chaseolson.pets.core.TokenResponseDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RefreshEndpoint {
    @FormUrlEncoded
    @POST("oauth2/token")
    suspend fun getToken(
            @Field("grant_type") type: String = "client_credentials",
            @Field("client_id") key: String = BuildConfig.petFinderKey,
            @Field("client_secret") secret: String = BuildConfig.petFinderSecret
    ): Response<TokenResponseDto>
}