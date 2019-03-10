package dev.dvdciri.sampleapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dev.dvdciri.sampleapp.data.di.NetworkModule
import dev.dvdciri.sampleapp.data.di.PostDataModule
import dev.dvdciri.sampleapp.data.di.UserDataModule
import dev.dvdciri.sampleapp.data.source.JsonPlaceholderClient
import dev.dvdciri.sampleapp.framework.ScheduleProvider
import dev.dvdciri.sampleapp.framework.viewmodel.ViewModelModule
import dev.dvdciri.sampleapp.post.PostListActivity
import dev.dvdciri.sampleapp.postdetails.PostDetailsActivity
import javax.inject.Singleton

// This could be split into feature sub-component when scaling up with the project
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        PostDataModule::class,
        UserDataModule::class,
        PresentationModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(postListActivity: PostListActivity)

    fun inject(postDetailsActivity: PostDetailsActivity)

    fun schedulerProvider(): ScheduleProvider

    fun jsonPlaceholderClient(): JsonPlaceholderClient
}