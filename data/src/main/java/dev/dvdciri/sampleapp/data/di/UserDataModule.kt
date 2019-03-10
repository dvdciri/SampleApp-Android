package dev.dvdciri.sampleapp.data.di

import dagger.Binds
import dagger.Module
import dev.dvdciri.sampleapp.data.UserRepositoryImpl
import dev.dvdciri.sampleapp.domain.repository.UserRepository

@Module
abstract class UserDataModule {

    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}