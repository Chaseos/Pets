package com.chaseolson.pets

import android.app.Application
import com.chaseolson.pets.network.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

@kotlinx.serialization.UnstableDefault
class PetsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@PetsApplication)
            modules(NetworkModule().retrofitModule)
        }
    }
}