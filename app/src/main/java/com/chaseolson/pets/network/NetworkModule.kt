package com.chaseolson.pets.network

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.chaseolson.pets.ui.home.HomeScreenViewModel
import com.chaseolson.pets.network.endpoints.PetFinderEndpoints
import com.chaseolson.pets.network.endpoints.RefreshEndpoint
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.Dispatcher
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@kotlinx.serialization.UnstableDefault
class NetworkModule {
    val retrofitModule = module {
        single { tokenPreferences(androidApplication()) }
        single { provideRefreshInterceptor(get()) }
        single { provideMobileEndpoints(get()) }
        single { HomeScreenViewModel(get()) }
    }

    val BASE_URL = "https://api.petfinder.com/v2/"

    private fun provideRefreshInterceptor(tokenPreferences: SharedPreferences) =
        OAuthInterceptor(
            Retrofit
                .Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl(BASE_URL)
                .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
                .build()
                .create(RefreshEndpoint::class.java), tokenPreferences
        )

    private fun provideMobileEndpoints(interceptor: OAuthInterceptor): PetFinderEndpoints {
        val nonstrict = Json(
            JsonConfiguration(
                isLenient = true,
                ignoreUnknownKeys = true,
                serializeSpecialFloatingPointValues = true,
                useArrayPolymorphism = true
            )
        )

        return Retrofit.Builder()
            .client(
                OkHttpClient
                    .Builder()
                    .dispatcher(Dispatcher().apply { maxRequests = 5 })
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build()
            )
            .baseUrl(BASE_URL)
            .addConverterFactory(nonstrict.asConverterFactory(MediaType.get("application/json")))
            .build()
            .create(PetFinderEndpoints::class.java)
    }

    private val TOKEN_FILE_KEY = "com.chaseolson.pets.token"
    private fun tokenPreferences(app: Application) =
        app.getSharedPreferences(TOKEN_FILE_KEY, Context.MODE_PRIVATE)
}