package dev.dvdciri.sampleapp.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import dev.dvdciri.sampleapp.domain.model.Comment
import dev.dvdciri.sampleapp.domain.repository.PostRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetCommentsByPostIdUseCaseTest {

    private lateinit var cut: GetCommentsByPostIdUseCase

    @Mock
    private lateinit var postRepository: PostRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = GetCommentsByPostIdUseCase(postRepository)
    }

    @Test
    fun `given posts returned by repository when subscribe to use case then correct posts returned`() {
        // Given
        val postId = 54
        val postList = listOf<Comment>(mock())
        whenever(postRepository.getComments(postId)).thenReturn(Single.just(postList))

        // When
        val testObserver = cut.buildUseCase(postId).test()

        // Then
        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(postList)
    }

    @Test
    fun `given error returned by repository when subscribe to use case then error propagated`() {
        // Given
        val postId = 54
        whenever(postRepository.getComments(postId)).thenReturn(Single.error(IllegalStateException()))

        // When
        val testObserver = cut.buildUseCase(postId).test()

        // Then
        testObserver
            .assertError(IllegalStateException::class.java)
    }
}