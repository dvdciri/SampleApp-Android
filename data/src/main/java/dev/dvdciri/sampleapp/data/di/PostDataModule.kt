package dev.dvdciri.sampleapp.data.di

import dagger.Binds
import dagger.Module
import dev.dvdciri.sampleapp.data.PostRepositoryImpl
import dev.dvdciri.sampleapp.domain.repository.PostRepository

@Module
abstract class PostDataModule {

    @Binds
    abstract fun providePostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository
}