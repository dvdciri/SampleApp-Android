package dev.dvdciri.sampleapp.post.mapper

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import dev.dvdciri.sampleapp.domain.model.Post
import dev.dvdciri.sampleapp.post.model.PostUiModel
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class PostToPostUiModelMapperTest {

    private lateinit var cut: PostToPostUiModelMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = PostToPostUiModelMapper()
    }

    @Test
    fun `given post when mapToPresentation then correct ui model returned`() {
        // Given
        val post = mock<Post> {
            on { it.id } doReturn 1234
            on { it.title } doReturn "title"
            on { it.body } doReturn "body"
        }

        // When
        val actualPostUiModel = cut.mapToPresentation(post)

        // Then
        val expectedPostUiModel = PostUiModel(
            "1234",
            "Title",
            "Body"
        )
        assertEquals(
            expectedPostUiModel,
            actualPostUiModel
        )
    }

    @Test
    fun `given post list when mapToPresentation then correct ui model list returned`() {
        // Given
        val post = mock<Post> {
            on { it.id } doReturn 1234
            on { it.title } doReturn "title"
            on { it.body } doReturn "body"
        }

        // When
        val actualPosListtUiModel = cut.mapToPresentation(listOf(post, post))

        // Then
        val expectedPostUiModel = PostUiModel(
            "1234",
            "Title",
            "Body"
        )
        assertEquals(
            listOf(expectedPostUiModel, expectedPostUiModel),
            actualPosListtUiModel
        )
    }
}