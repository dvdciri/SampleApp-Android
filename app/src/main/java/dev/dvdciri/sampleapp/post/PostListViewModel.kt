package dev.dvdciri.sampleapp.post

import android.arch.lifecycle.MutableLiveData
import android.support.annotation.StringRes
import android.util.Log
import dev.dvdciri.sampleapp.R
import dev.dvdciri.sampleapp.domain.model.Post
import dev.dvdciri.sampleapp.domain.usecase.GetPostsUseCase
import dev.dvdciri.sampleapp.framework.ScheduleProvider
import dev.dvdciri.sampleapp.framework.viewmodel.BaseViewModel
import dev.dvdciri.sampleapp.framework.viewmodel.SingleLiveEvent
import dev.dvdciri.sampleapp.framework.viewmodel.viewstate.ErrorUiModel
import dev.dvdciri.sampleapp.post.mapper.PostToPostUiModelMapper
import dev.dvdciri.sampleapp.postdetails.DetailsNavigationParams
import dev.dvdciri.sampleapp.ui.ItemUiModel
import dev.dvdciri.sampleapp.ui.skeleton.SkeletonUiModelCreator


private const val SKELETON_ITEM_COUNT = 50

class PostListViewModel
constructor(
    private val scheduleProvider: ScheduleProvider,
    private val getPostsUseCase: GetPostsUseCase,
    private val postToPostUiModelMapper: PostToPostUiModelMapper,
    private val skeletonUiModelCreator: SkeletonUiModelCreator
) : BaseViewModel() {

    val postListViewState = MutableLiveData<PostListViewState>()
    val navigateToDetails = SingleLiveEvent<DetailsNavigationParams>()

    private val postList: MutableList<Post> = mutableListOf()

    fun loadPosts() {
        val disposable = getPostsUseCase.buildUseCase()
            .doOnSubscribe { postSkeletonDataState() }
            .doOnSuccess {
                postList.clear()
                postList.addAll(it)
            }
            .map { postToPostUiModelMapper.mapToPresentation(it) }
            .map { createDataState(it) }
            .subscribeOn(scheduleProvider.io())
            .subscribe(
                { postListViewState.postValue(it) },
                {
                    Log.e("PostListViewModel", it.toString(), it)
                    postListViewState.postValue(createErrorState(R.string.post_list_generic_error))
                }
            )
        compositeDisposable.add(disposable)
    }

    fun onItemClicked(position: Int) {
        val post = postList[position]
        navigateToDetails.postValue(DetailsNavigationParams(post.id))
    }

    private fun postSkeletonDataState() {
        postListViewState.postValue(createDataState(skeletonUiModelCreator.createUiModels(SKELETON_ITEM_COUNT)))
    }

    private fun createErrorState(@StringRes message: Int): PostListViewState {
        return PostListViewState(
            ErrorUiModel.Message(
                message
            ), listOf()
        )
    }

    private fun createDataState(uiModelList: List<ItemUiModel>): PostListViewState {
        return PostListViewState(
            ErrorUiModel.None,
            uiModelList
        )
    }
}
