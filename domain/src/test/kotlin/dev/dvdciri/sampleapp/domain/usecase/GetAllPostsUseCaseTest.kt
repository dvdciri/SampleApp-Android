package dev.dvdciri.sampleapp.domain.usecase

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import dev.dvdciri.sampleapp.domain.model.Post
import dev.dvdciri.sampleapp.domain.repository.PostRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetAllPostsUseCaseTest {

    private lateinit var cut: GetPostsUseCase

    @Mock
    private lateinit var postRepository: PostRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = GetPostsUseCase(postRepository)
    }

    @Test
    fun `given posts returned by repository when subscribe to use case then correct posts returned`() {
        // Given
        val postList = listOf<Post>(mock())
        whenever(postRepository.getPosts()).thenReturn(Single.just(postList))

        // When
        val testObserver = cut.buildUseCase().test()

        // Then
        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(postList)
    }

    @Test
    fun `given error returned by repository when subscribe to use case then error returned`() {
        // Given
        val postList = listOf<Post>(mock())
        whenever(postRepository.getPosts()).thenReturn(Single.error(IllegalStateException()))

        // When
        val testObserver = cut.buildUseCase().test()

        // Then
        testObserver
            .assertError(IllegalStateException::class.java)
    }
}