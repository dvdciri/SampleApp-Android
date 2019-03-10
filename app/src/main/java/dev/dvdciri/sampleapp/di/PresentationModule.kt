package dev.dvdciri.sampleapp.di

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.dvdciri.sampleapp.framework.viewmodel.ViewModelKey
import dev.dvdciri.sampleapp.post.PostListViewModel
import dev.dvdciri.sampleapp.postdetails.PostDetailsViewModel

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    abstract fun bindPostListViewModel(viewModel: PostListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostDetailsViewModel::class)
    abstract fun bindPostDetailsViewModel(viewModel: PostDetailsViewModel): ViewModel
}
