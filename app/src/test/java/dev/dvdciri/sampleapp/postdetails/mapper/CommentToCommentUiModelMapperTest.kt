package dev.dvdciri.sampleapp.postdetails.mapper

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import dev.dvdciri.sampleapp.domain.model.Comment
import dev.dvdciri.sampleapp.postdetails.model.CommentUiModel
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class CommentToCommentUiModelMapperTest {

    private lateinit var cut: CommentToCommentUiModelMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = CommentToCommentUiModelMapper()
    }

    @Test
    fun `given comment when mapToPresentation then correct ui model returned`() {
        // Given
        val id = 1234
        val email = "email"
        val body = "body"
        val comment = mock<Comment>{
            on {it.id} doReturn id
            on {it.email} doReturn email
            on {it.body} doReturn body
        }

        // When
        val actual = cut.mapToPresentation(comment)

        val expected = CommentUiModel(
            id = "1234",
            content = body,
            title = email,
            avatarUrl = "https://api.adorable.io/avatars/180/1234.png"
        )
        // Then
        assertEquals(expected, actual)
    }

    @Test
    fun `given comment list when mapToPresentation then correct ui model list returned`() {
        // Given
        val id = 1234
        val email = "email"
        val body = "body"
        val comment = mock<Comment>{
            on {it.id} doReturn id
            on {it.email} doReturn email
            on {it.body} doReturn body
        }

        // When
        val actual = cut.mapToPresentation(listOf(comment, comment))

        val expected = CommentUiModel(
            id = "1234",
            content = body,
            title = email,
            avatarUrl = "https://api.adorable.io/avatars/180/1234.png"
        )
        // Then
        assertEquals(listOf(expected, expected), actual)
    }
}