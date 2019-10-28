package com.chaseolson.pets

import android.app.Application
import com.chaseolson.pets.module.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PetsApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@PetsApplication)
            modules(NetworkModule().retrofitModule)
        }
    }
}