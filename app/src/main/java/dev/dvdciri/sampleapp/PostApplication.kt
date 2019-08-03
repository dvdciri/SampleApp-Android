package dev.dvdciri.sampleapp

import android.app.Application
import dev.dvdciri.sampleapp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PostApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PostApplication)
            modules(appModules)
        }
    }
}