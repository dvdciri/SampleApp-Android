package dev.dvdciri.sampleapp.postdetails

import android.arch.lifecycle.MutableLiveData
import android.support.annotation.StringRes
import dev.dvdciri.sampleapp.R
import dev.dvdciri.sampleapp.domain.model.Comment
import dev.dvdciri.sampleapp.domain.model.PostDetails
import dev.dvdciri.sampleapp.domain.usecase.GetCommentsByPostIdUseCase
import dev.dvdciri.sampleapp.domain.usecase.GetPostDetailsByPostIdUseCase
import dev.dvdciri.sampleapp.framework.ScheduleProvider
import dev.dvdciri.sampleapp.framework.viewmodel.BaseViewModel
import dev.dvdciri.sampleapp.framework.viewmodel.viewstate.ErrorUiModel
import dev.dvdciri.sampleapp.postdetails.mapper.CommentListToItemUiModelMapper
import dev.dvdciri.sampleapp.postdetails.mapper.PostDetailsToUiModelsMapper
import dev.dvdciri.sampleapp.ui.ItemUiModel


class PostDetailsViewModel
constructor(
    private val scheduleProvider: ScheduleProvider,
    private val getPostDetailsByPostIdUseCase: GetPostDetailsByPostIdUseCase,
    private val getCommentsByPostIdUseCase: GetCommentsByPostIdUseCase,
    private val postDetailsToUiModelsMapper: PostDetailsToUiModelsMapper,
    private val commentListToItemUiModelMapper: CommentListToItemUiModelMapper
) : BaseViewModel() {

    val postDetailsViewState = MutableLiveData<PostDetailsViewState>()

    private lateinit var postDetails: PostDetails

    fun loadPostDetails(detailsNavigationParams: DetailsNavigationParams) {
        val disposable = getPostDetailsByPostIdUseCase.buildUseCase(detailsNavigationParams.postId)
            .doOnSuccess { postDetails = it }
            .map { mapToUiModels(it, emptyList()) }
            .map { createDataState(it) }
            .subscribeOn(scheduleProvider.io())
            .subscribe(
                {
                    postDetailsViewState.postValue(it)
                    loadComments(detailsNavigationParams.postId)
                },
                { postDetailsViewState.postValue(createErrorState(R.string.post_details_generic_error)) }
            )
        compositeDisposable.add(disposable)
    }

    private fun loadComments(postId: Int) {
        val disposable = getCommentsByPostIdUseCase.buildUseCase(postId)
            .map { mapToUiModels(postDetails, it) }
            .map { createDataState(it) }
            .subscribeOn(scheduleProvider.io())
            .subscribe { it -> postDetailsViewState.postValue(it) }
        compositeDisposable.add(disposable)
    }

    private fun mapToUiModels(postDetails: PostDetails, commentList: List<Comment>) =
        postDetailsToUiModelsMapper.mapToPresentation(postDetails) + commentListToItemUiModelMapper.mapToPresentation(
            commentList
        )

    private fun creteRandomImageUrl(): String {
        return "https://picsum.photos/400/400/?random"
    }

    private fun createErrorState(@StringRes message: Int): PostDetailsViewState {
        return PostDetailsViewState(ErrorUiModel.Message(message), "", listOf())
    }

    private fun createDataState(uiModelList: List<ItemUiModel>): PostDetailsViewState {
        return PostDetailsViewState(ErrorUiModel.None, creteRandomImageUrl(), uiModelList)
    }
}
