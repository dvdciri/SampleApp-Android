package dev.dvdciri.sampleapp.data.mapper

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import dev.dvdciri.sampleapp.data.model.CommentDto
import dev.dvdciri.sampleapp.domain.model.Comment
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class CommentDtoToCommentMapperTest {

    private lateinit var cut: CommentDtoToCommentMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = CommentDtoToCommentMapper()
    }

    @Test
    fun `given comment list dto when mapToDomain then correct domain models returned`() {
        // Given
        val id = 1
        val name = "name"
        val email = "email"
        val body = "body"
        val comment = mock<CommentDto> {
            on { it.id } doReturn id
            on { it.name } doReturn name
            on { it.email } doReturn email
            on { it.body } doReturn body
        }

        // When
        val commentList = cut.mapToDomain(listOf(comment, comment))

        // Then
        val expectedComment = Comment(id, name, email, body)
        assertEquals(listOf(
            expectedComment,
            expectedComment
        ), commentList)
    }
}