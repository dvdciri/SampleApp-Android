package dev.dvdciri.sampleapp.postdetails.mapper

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import dev.dvdciri.sampleapp.domain.model.Post
import dev.dvdciri.sampleapp.domain.model.PostDetails
import dev.dvdciri.sampleapp.domain.model.User
import dev.dvdciri.sampleapp.post.mapper.PostToPostUiModelMapper
import dev.dvdciri.sampleapp.post.model.PostUiModel
import dev.dvdciri.sampleapp.postdetails.model.AuthorUiModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class PostDetailsToUiModelsMapperTest {

    private lateinit var cut: PostDetailsToUiModelsMapper

    @Mock private lateinit var userToAuthorUiModelMapper: UserToAuthorUiModelMapper
    @Mock private lateinit var postToPostUiModelMapper: PostToPostUiModelMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = PostDetailsToUiModelsMapper(userToAuthorUiModelMapper, postToPostUiModelMapper)
    }

    @Test
    fun `given post details when mapToPresentation then correct ui list returned`() {
        // Given
        val author = mock<User>()
        val post = mock<Post>()
        val postDetails = mock<PostDetails> {
            on {it.author} doReturn author
            on {it.post} doReturn post
        }

        val authorUiModel = mock<AuthorUiModel>()
        whenever(userToAuthorUiModelMapper.mapToPresentation(author)).thenReturn(authorUiModel)

        val postUiModel = mock<PostUiModel>()
        whenever(postToPostUiModelMapper.mapToPresentation(post)).thenReturn(postUiModel)

        // When
        val uiModelList = cut.mapToPresentation(postDetails)

        // Then
        val expected = listOf(postUiModel, authorUiModel)
        assertEquals(expected, uiModelList)
    }
}