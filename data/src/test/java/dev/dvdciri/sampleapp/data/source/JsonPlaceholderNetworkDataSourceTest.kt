package dev.dvdciri.sampleapp.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import dev.dvdciri.sampleapp.data.model.CommentDto
import dev.dvdciri.sampleapp.data.model.PostDto
import dev.dvdciri.sampleapp.data.model.UserDto
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class JsonPlaceholderNetworkDataSourceTest {

    private lateinit var cut: JsonPlaceholderNetworkDataSource

    @Mock private lateinit var jsonPlaceholderClient: JsonPlaceholderClient

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = JsonPlaceholderNetworkDataSource(jsonPlaceholderClient)
    }

    @Test
    fun `given post dto list returned by client when getPostDtoList then correct list returned`() {
        // Given
        val postDtoList = listOf<PostDto>(mock())
        whenever(jsonPlaceholderClient.getPostDtoList()).thenReturn(Single.just(postDtoList))

        // When
        val testObserver = cut.getPostDtoList().test()

        // Then
        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(postDtoList)
    }

    @Test
    fun `given postDto returned by client by id when getPostDto then correct post dto returned`() {
        // Given
        val postId = 65

        val postDto = mock<PostDto>()
        whenever(jsonPlaceholderClient.getPostDto(postId)).thenReturn(Single.just(postDto))

        // When
        val testObserver = cut.getPostDto(postId).test()

        // Then
        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(postDto)
    }

    @Test
    fun `given comment dto list returned by client when getCommentDtoList then correct comment dto list returned`() {
        // Given
        val postId = 8768

        val commentDtoList = listOf<CommentDto>(mock())
        whenever(jsonPlaceholderClient.getCommentDtoList(postId)).thenReturn(Single.just(commentDtoList))

        // When
        val testObserver = cut.getCommentDtoList(postId).test()

        // Then
        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(commentDtoList)
    }

    @Test
    fun `given user dto returned from client when getUserDto then correct dto returned`() {
        // Given
        val userId = 765765

        val userDto = mock<UserDto>()
        whenever(jsonPlaceholderClient.getUserDto(userId)).thenReturn(Single.just(userDto))

        // When
        val testObserver = cut.getUserDto(userId).test()

        // Then
        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(userDto)
    }
}