package dev.dvdciri.sampleapp.domain.usecase

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import dev.dvdciri.sampleapp.domain.model.Post
import dev.dvdciri.sampleapp.domain.model.PostDetails
import dev.dvdciri.sampleapp.domain.model.User
import dev.dvdciri.sampleapp.domain.repository.PostRepository
import dev.dvdciri.sampleapp.domain.repository.UserRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetPostDetailsByPostIdUseCaseTest {

    private lateinit var cut: GetPostDetailsByPostIdUseCase

    @Mock
    private lateinit var postRepository: PostRepository
    @Mock
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = GetPostDetailsByPostIdUseCase(postRepository, userRepository)
    }

    @Test
    fun `given post repository returns post and user repository returns user when subscribe then author id used to fetch user and correct value returned`() {
        // Given
        val postId = 45
        val authorId = 88

        val post = mock<Post> {
            on { it.authorId } doReturn authorId
        }
        whenever(postRepository.getPost(postId)).thenReturn(Single.just(post))

        val user = mock<User>()
        whenever(userRepository.getUser(authorId)).thenReturn(Single.just(user))

        // When
        val testObserver = cut.buildUseCase(postId).test()

        // Then
        val expectedValue = PostDetails(post, user)

        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(expectedValue)
    }

    @Test
    fun `given post repository returns error when subscribe then error emitted`() {
        // Given
        val postId = 45

        whenever(postRepository.getPost(postId)).thenReturn(Single.error(IllegalStateException()))

        // When
        val testObserver = cut.buildUseCase(postId).test()

        // Then
        testObserver
            .assertError(IllegalStateException::class.java)
    }

    @Test
    fun `given user repository returns error when subscribe then error emitted`() {
        // Given
        val postId = 45
        val authorId = 88

        val post = mock<Post> {
            on { it.authorId } doReturn authorId
        }
        whenever(postRepository.getPost(postId)).thenReturn(Single.just(post))

        val user = mock<User>()
        whenever(userRepository.getUser(authorId)).thenReturn(Single.error(IllegalStateException()))

        // When
        val testObserver = cut.buildUseCase(postId).test()

        // Then
        testObserver
            .assertError(IllegalStateException::class.java)
    }
}