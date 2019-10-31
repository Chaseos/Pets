package com.chaseolson.pets.core

import android.content.SharedPreferences
import com.chaseolson.pets.repo.RefreshEndpoint
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class OAuthInterceptor(val refreshEndpoint: RefreshEndpoint, val tokenPreferences: SharedPreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        synchronized(this) {
            val token = tokenPreferences.getString("token", null)
            val originalRequest = chain.request()
            val authenticationRequest = originalRequest.newBuilder().addHeader("Authorization", "Bearer $token").build()
            val initialResponse = chain.proceed(authenticationRequest)

            return when {
                initialResponse.code() == 403 || initialResponse.code() == 401 -> {
                    val responseNewTokenLoginModel = runBlocking { refreshEndpoint.getToken() }

                    if (responseNewTokenLoginModel.isSuccessful) {
                        val newToken = responseNewTokenLoginModel.body()?.accessToken?.let { token ->
                            tokenPreferences.edit().putString("token", token)
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