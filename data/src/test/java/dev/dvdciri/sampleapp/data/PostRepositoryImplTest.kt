package dev.dvdciri.sampleapp.data

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import dev.dvdciri.sampleapp.data.mapper.CommentDtoToCommentMapper
import dev.dvdciri.sampleapp.data.mapper.PostDtoToPostMapper
import dev.dvdciri.sampleapp.data.model.CommentDto
import dev.dvdciri.sampleapp.data.model.PostDto
import dev.dvdciri.sampleapp.data.source.JsonPlaceholderNetworkDataSource
import dev.dvdciri.sampleapp.domain.model.Comment
import dev.dvdciri.sampleapp.domain.model.Post
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PostRepositoryImplTest {

    private lateinit var cut: PostRepositoryImpl

    @Mock
    private lateinit var jsonPlaceholderNetworkDataSource: JsonPlaceholderNetworkDataSource
    @Mock
    private lateinit var postDtoToPostMapper: PostDtoToPostMapper
    @Mock
    private lateinit var commentDtoToCommentMapper: CommentDtoToCommentMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = PostRepositoryImpl(jsonPlaceholderNetworkDataSource, postDtoToPostMapper, commentDtoToCommentMapper)
    }

    @Test
    fun `given comment dto list returned by client when getComments then dto mapped and domain model returned`() {
        // Given
        val postId = 7832649

        val commentDtoList = listOf<CommentDto>(mock())
        whenever(jsonPlaceholderNetworkDataSource.getCommentDtoList(postId)).doReturn(Single.just(commentDtoList))

        val commentList = listOf<Comment>(mock())
        whenever(commentDtoToCommentMapper.mapToDomain(commentDtoList)).thenReturn(commentList)

        // When
        val testObserver = cut.getComments(postId).test()

        // Then
        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(commentList)
    }

    @Test
    fun `given post dto returned by client when getPost then dto mapped and domain model returned`() {
        // Given
        val postId = 76876

        val postDto = mock<PostDto>()
        whenever(jsonPlaceholderNetworkDataSource.getPostDto(postId)).thenReturn(Single.just(postDto))

        val post = mock<Post>()
        whenever(postDtoToPostMapper.mapToDomain(postDto)).thenReturn(post)

        // When
        val testObserver = cut.getPost(postId).test()

        // Then
        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(post)
    }

    @Test
    fun `given post dto list returned by client when getPosts then post dto mapped and domain returned`() {
        // Given
        val postDtoList = listOf(mock<PostDto>())
        whenever(jsonPlaceholderNetworkDataSource.getPostDtoList()).thenReturn(Single.just(postDtoList))

        val postList = listOf<Post>(mock())
        whenever(postDtoToPostMapper.mapToDomain(postDtoList)).thenReturn(postList)

        // When
        val testObserver = cut.getPosts().test()

        // Then
        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(postList)
    }
}