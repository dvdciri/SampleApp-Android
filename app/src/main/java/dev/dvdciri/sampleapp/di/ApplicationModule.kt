package dev.dvdciri.sampleapp.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dev.dvdciri.sampleapp.framework.ScheduleProvider
import dev.dvdciri.sampleapp.framework.SchedulerProviderImpl
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideResources(application: Application): Resources = application.resources

    @Provides
    @Singleton
    fun provideSchedulerProvider(schedulerProviderImpl: SchedulerProviderImpl): ScheduleProvider = schedulerProviderImpl
}