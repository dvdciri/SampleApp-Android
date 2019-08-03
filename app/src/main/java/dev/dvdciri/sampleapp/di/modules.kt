package dev.dvdciri.sampleapp.di

import dev.dvdciri.sampleapp.data.di.dataModule
import dev.dvdciri.sampleapp.data.di.networkModule
import dev.dvdciri.sampleapp.domain.di.domainModule
import dev.dvdciri.sampleapp.framework.ScheduleProvider
import dev.dvdciri.sampleapp.framework.SchedulerProviderImpl
import dev.dvdciri.sampleapp.framework.mapper.ItemUiModelIdGenerator
import dev.dvdciri.sampleapp.post.PostListViewModel
import dev.dvdciri.sampleapp.post.mapper.PostToPostUiModelMapper
import dev.dvdciri.sampleapp.postdetails.PostDetailsViewModel
import dev.dvdciri.sampleapp.postdetails.mapper.*
import dev.dvdciri.sampleapp.ui.skeleton.SkeletonUiModelCreator
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules by lazy {
    listOf(
        networkModule,
        dataModule,
        domainModule,
        presentationModule
    )
}

val presentationModule = module {

    single { androidApplication().resources }

    // View Model
    viewModel { PostListViewModel(get(), get(), get(), get()) }
    viewModel { PostDetailsViewModel(get(), get(), get(), get(), get()) }
    single<ScheduleProvider> { SchedulerProviderImpl() }

    // Mappers
    factory { PostToPostUiModelMapper() }
    factory { SkeletonUiModelCreator() }
    factory { PostDetailsToUiModelsMapper(get(), get()) }
    factory { CommentListToItemUiModelMapper(get(), get()) }
    factory { UserToAuthorUiModelMapper(get()) }
    factory { CommentToCommentUiModelMapper() }
    factory { CommentListToCommentInfoUiModelMapper(get(), get()) }
    factory { ItemUiModelIdGenerator() }
}
