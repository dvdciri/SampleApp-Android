package dev.dvdciri.sampleapp.post

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.support.annotation.StringRes
import com.nhaarman.mockito_kotlin.*
import dev.dvdciri.sampleapp.R
import dev.dvdciri.sampleapp.domain.model.Post
import dev.dvdciri.sampleapp.domain.usecase.GetPostsUseCase
import dev.dvdciri.sampleapp.framework.ScheduleProvider
import dev.dvdciri.sampleapp.framework.viewmodel.viewstate.ErrorUiModel
import dev.dvdciri.sampleapp.post.mapper.PostToPostUiModelMapper
import dev.dvdciri.sampleapp.post.model.PostUiModel
import dev.dvdciri.sampleapp.postdetails.DetailsNavigationParams
import dev.dvdciri.sampleapp.ui.ItemUiModel
import dev.dvdciri.sampleapp.ui.skeleton.SkeletonUiModelCreator
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

private const val SKELETON_ITEM_COUNT = 50

class PostListViewModelTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    private lateinit var cut: PostListViewModel

    @Mock private lateinit var scheduleProvider: ScheduleProvider
    @Mock private lateinit var getPostsUseCase: GetPostsUseCase
    @Mock private lateinit var postToPostUiModelMapper: PostToPostUiModelMapper
    @Mock private lateinit var skeletonUiModelCreator: SkeletonUiModelCreator

    @Mock lateinit var postListViewStateObserver: Observer<PostListViewState>
    @Mock lateinit var detailsNavigationParamsEvent: Observer<DetailsNavigationParams>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        whenever(scheduleProvider.io()).thenReturn(Schedulers.trampoline())

        cut = PostListViewModel(scheduleProvider, getPostsUseCase, postToPostUiModelMapper, skeletonUiModelCreator)
        cut.postListViewState.observeForever(postListViewStateObserver)
        cut.navigateToDetails.observeForever(detailsNavigationParamsEvent)
    }

    @Test
    fun `given post returned from used case when loadPosts then skeleton state followed by post ui model`() {
        // Given
        val postList = listOf<Post>(mock())
        whenever(getPostsUseCase.buildUseCase()).thenReturn(Single.just(postList))

        val postUiModels = listOf<PostUiModel>(mock())
        whenever(postToPostUiModelMapper.mapToPresentation(postList)).thenReturn(postUiModels)

        val skeletonUiModels = listOf<ItemUiModel>(mock())
        whenever(skeletonUiModelCreator.createUiModels(SKELETON_ITEM_COUNT)).thenReturn(skeletonUiModels)

        // When
        cut.loadPosts()

        // Then
        inOrder(postListViewStateObserver) {
            verify(postListViewStateObserver).onChanged(createDataState(skeletonUiModels))
            verify(postListViewStateObserver).onChanged(createDataState(postUiModels))
        }
    }

    @Test
    fun `given error from get post use case when loadPosts then skeleton state followed by error state`() {
        // Given
        whenever(getPostsUseCase.buildUseCase()).thenReturn(Single.error(IllegalStateException()))

        val skeletonUiModels = listOf<ItemUiModel>(mock())
        whenever(skeletonUiModelCreator.createUiModels(SKELETON_ITEM_COUNT)).thenReturn(skeletonUiModels)

        // When
        cut.loadPosts()

        // Then
        inOrder(postListViewStateObserver) {
            verify(postListViewStateObserver).onChanged(createDataState(skeletonUiModels))
            verify(postListViewStateObserver).onChanged(createErrorState(R.string.post_list_generic_error))
        }
    }

    @Test
    fun `given two post loaded when onItemClicked at position one then correct navigation event sent`() {
        // Given
        val postClicked = mock<Post> {
            on { it.id } doReturn 12345
        }
        val postList = listOf(mock(), postClicked)
        whenever(getPostsUseCase.buildUseCase()).thenReturn(Single.just(postList))

        val postUiModels = listOf<PostUiModel>(mock(), mock())
        whenever(postToPostUiModelMapper.mapToPresentation(postList)).thenReturn(postUiModels)

        val skeletonUiModels = listOf<ItemUiModel>(mock())
        whenever(skeletonUiModelCreator.createUiModels(SKELETON_ITEM_COUNT)).thenReturn(skeletonUiModels)

        cut.loadPosts()

        // When
        cut.onItemClicked(1)

        // Then
        verify(detailsNavigationParamsEvent).onChanged(DetailsNavigationParams(12345))
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