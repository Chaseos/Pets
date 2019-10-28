package com.chaseolson.pets.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.chaseolson.pets.core.OAuthInterceptor
import com.chaseolson.pets.newhome.HomeScreenViewModel2
import com.chaseolson.pets.repo.MobileEndpointsNew
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import kotlinx.serialization.json.Json
import okhttp3.Dispatcher
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class NetworkModule {
    val retrofitModule = module {
        single { accessToken(androidApplication()) }
        single(named("tokenPrefs")) { tokenPreferences(androidApplication()) }
        single { okHttp(get<Retrofit>().create(MobileEndpointsNew::class.java), get(named("tokenPrefs")), get()) }
        single { retrofit(get(), "http://api.petfinder.com/v2/") }
        single { get<Retrofit>().create(MobileEndpointsNew::class.java) }
        single { HomeScreenViewModel2(get()) }
    }

    private fun okHttp(repo: MobileEndpointsNew, tokenPreferences: SharedPreferences, token: String?) = OkHttpClient
            .Builder()
            .dispatcher(Dispatcher().apply { maxRequests = 1 })
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(OAuthInterceptor(repo, tokenPreferences, token))
            .build()


    private fun retrofit(okHttp: OkHttpClient, baseUrl: String) = Retrofit.Builder()
            .client(okHttp)
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()

    val picassoModule = module {
        single { picasso(androidContext(), okHttp3Downloader(get())) }
    }

    private fun okHttp3Downloader(client: OkHttpClient) = OkHttp3Downloader(client)
    private fun picasso(context: Context, downloader: OkHttp3Downloader) =
            Picasso.Builder(context).downloader(downloader)

    val preferencesModule = module {
        single(named("tokenPrefs")) { tokenPreferences(androidApplication()) }
    }

    private val TOKEN_FILE_KEY = "com.chaseolson.pets.token"

    private fun tokenPreferences(app: Application) =
            app.getSharedPreferences(TOKEN_FILE_KEY, Context.MODE_PRIVATE)

    private fun accessToken(app: Application): String? {
        val sharedPrefs = app.getSharedPreferences(TOKEN_FILE_KEY, Context.MODE_PRIVATE)
        return sharedPrefs.getString("token", null)
    }

}