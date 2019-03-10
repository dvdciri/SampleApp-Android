package dev.dvdciri.sampleapp.di

import android.app.Application

object ApplicationComponentHolder {

    private lateinit var applicationComponent: ApplicationComponent

    fun get(application: Application): ApplicationComponent {
        if (!this::applicationComponent.isInitialized) {
            applicationComponent = DaggerApplicationComponent.builder().application(application).build()
        }
        return applicationComponent
    }
}