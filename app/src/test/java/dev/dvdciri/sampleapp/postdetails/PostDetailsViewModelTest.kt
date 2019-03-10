package dev.dvdciri.sampleapp.postdetails

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.support.annotation.StringRes
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import dev.dvdciri.sampleapp.R
import dev.dvdciri.sampleapp.domain.model.Comment
import dev.dvdciri.sampleapp.domain.model.PostDetails
import dev.dvdciri.sampleapp.domain.usecase.GetCommentsByPostIdUseCase
import dev.dvdciri.sampleapp.domain.usecase.GetPostDetailsByPostIdUseCase
import dev.dvdciri.sampleapp.framework.ScheduleProvider
import dev.dvdciri.sampleapp.framework.viewmodel.viewstate.ErrorUiModel
import dev.dvdciri.sampleapp.postdetails.mapper.CommentListToItemUiModelMapper
import dev.dvdciri.sampleapp.postdetails.mapper.PostDetailsToUiModelsMapper
import dev.dvdciri.sampleapp.ui.ItemUiModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PostDetailsViewModelTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    private lateinit var cut: PostDetailsViewModel

    @Mock
    private lateinit var scheduleProvider: ScheduleProvider
    @Mock
    private lateinit var getPostDetailsByPostIdUseCase: GetPostDetailsByPostIdUseCase
    @Mock
    private lateinit var getCommentsByPostIdUseCase: GetCommentsByPostIdUseCase
    @Mock
    private lateinit var postDetailsToUiModelsMapper: PostDetailsToUiModelsMapper
    @Mock
    private lateinit var commentListToItemUiModelMapper: CommentListToItemUiModelMapper

    @Mock
    lateinit var postDetailsViewStateObserver: Observer<PostDetailsViewState>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        whenever(scheduleProvider.io()).thenReturn(Schedulers.trampoline())

        cut = PostDetailsViewModel(
            scheduleProvider,
            getPostDetailsByPostIdUseCase,
            getCommentsByPostIdUseCase,
            postDetailsToUiModelsMapper,
            commentListToItemUiModelMapper
        )
        cut.postDetailsViewState.observeForever(postDetailsViewStateObserver)
    }

    @Test
    fun `given post id when loadPostDetails then details loaded first and then comment loaded afterwards`() {
        // Given
        val postId = 1234

        val postDetails = mock<PostDetails>()
        whenever(getPostDetailsByPostIdUseCase.buildUseCase(postId)).thenReturn(Single.just(postDetails))

        val postDetailsUiModelList = listOf<ItemUiModel>(mock())
        whenever(postDetailsToUiModelsMapper.mapToPresentation(postDetails)).thenReturn(postDetailsUiModelList)

        whenever(commentListToItemUiModelMapper.mapToPresentation(listOf())).thenReturn(listOf())

        val commentList = listOf<Comment>(mock())
        whenever(getCommentsByPostIdUseCase.buildUseCase(postId)).thenReturn(Single.just(commentList))

        val commentUiModelList = listOf<ItemUiModel>(mock())
        whenever(commentListToItemUiModelMapper.mapToPresentation(commentList)).thenReturn(commentUiModelList)

        // When
        cut.loadPostDetails(DetailsNavigationParams(postId))

        // Then
        inOrder(postDetailsViewStateObserver){
            verify(postDetailsViewStateObserver).onChanged(createDataState(postDetailsUiModelList))
            verify(postDetailsViewStateObserver).onChanged(createDataState(postDetailsUiModelList + commentUiModelList))
        }
    }

    @Test
    fun `given post id and post details error when loadPostDetails then screen error posted`() {
        // Given
        val postId = 1234

        whenever(getPostDetailsByPostIdUseCase.buildUseCase(postId)).thenReturn(Single.error(IllegalStateException()))

        // When
        cut.loadPostDetails(DetailsNavigationParams(postId))

        // Then
        inOrder(postDetailsViewStateObserver){
            verify(postDetailsViewStateObserver).onChanged(createErrorState(R.string.post_details_generic_error))
            verifyNoMoreInteractions()
        }
    }

    @Test
    fun `given post id and comment loading error when loadPostDetails then no error posted and only details state`() {
        // Given
        val postId = 1234

        val postDetails = mock<PostDetails>()
        whenever(getPostDetailsByPostIdUseCase.buildUseCase(postId)).thenReturn(Single.just(postDetails))

        val postDetailsUiModelList = listOf<ItemUiModel>(mock())
        whenever(postDetailsToUiModelsMapper.mapToPresentation(postDetails)).thenReturn(postDetailsUiModelList)

        whenever(commentListToItemUiModelMapper.mapToPresentation(listOf())).thenReturn(listOf())

        whenever(getCommentsByPostIdUseCase.buildUseCase(postId)).thenReturn(Single.error(IllegalStateException()))


        // When
        cut.loadPostDetails(DetailsNavigationParams(postId))

        // Then
        inOrder(postDetailsViewStateObserver){
            verify(postDetailsViewStateObserver).onChanged(createDataState(postDetailsUiModelList))
            verifyNoMoreInteractions()
        }
    }

    private fun createErrorState(@StringRes message: Int): PostDetailsViewState {
        return PostDetailsViewState(ErrorUiModel.Message(message), "", listOf())
    }

    private fun createDataState(uiModelList: List<ItemUiModel>): PostDetailsViewState {
        return PostDetailsViewState(ErrorUiModel.None, "https://picsum.photos/400/400/?random", uiModelList)
    }
}