package com.chaseolson.pets.core

import android.content.SharedPreferences
import androidx.core.content.edit
import com.chaseolson.pets.repo.MobileEndpointsNew
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class OAuthInterceptor(val repo: MobileEndpointsNew, val tokenPreferences: SharedPreferences, val accessToken: String?) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        synchronized(this) {

            val originalRequest = chain.request()
            val authenticationRequest = originalRequest.newBuilder().addHeader("Authorization", "Bearer $accessToken").build()
            val initialResponse = chain.proceed(authenticationRequest)

            return when {
                initialResponse.code() == 403 || initialResponse.code() == 401 -> {
                    val responseNewTokenLoginModel = runBlocking { repo.getToken() }

                    if (responseNewTokenLoginModel.isSuccessful) {
                        val newToken = responseNewTokenLoginModel.body()?.accessToken?.let { token ->
                            tokenPreferences.edit { putString("token", token) }
                            token
                        }
                        val newAuthenticationRequest = originalRequest.newBuilder().addHeader("Authorization", "Bearer $newToken").build()
                        chain.proceed(newAuthenticationRequest)
                    } else initialResponse
                }
                else -> initialResponse
            }
        }
    }
}