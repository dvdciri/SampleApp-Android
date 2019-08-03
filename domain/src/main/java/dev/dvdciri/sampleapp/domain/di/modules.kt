package dev.dvdciri.sampleapp.domain.di

import dev.dvdciri.sampleapp.domain.usecase.GetCommentsByPostIdUseCase
import dev.dvdciri.sampleapp.domain.usecase.GetPostDetailsByPostIdUseCase
import dev.dvdciri.sampleapp.domain.usecase.GetPostsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetPostsUseCase(get()) }
    factory { GetPostDetailsByPostIdUseCase(get(), get()) }
    factory { GetCommentsByPostIdUseCase(get()) }
}