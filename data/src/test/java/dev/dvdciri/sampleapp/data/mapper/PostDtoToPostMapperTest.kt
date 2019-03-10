package dev.dvdciri.sampleapp.data.mapper

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import dev.dvdciri.sampleapp.data.model.PostDto
import dev.dvdciri.sampleapp.domain.model.Post
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class PostDtoToPostMapperTest {

    private lateinit var cut: PostDtoToPostMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = PostDtoToPostMapper()
    }

    @Test
    fun `given post dto when mapToDomain then correct domain model returned`() {
        // Given
        val id = 1
        val authorId = 2
        val title = "title"
        val body = "body"
        val postDto = mock<PostDto> {
            on { it.id } doReturn id
            on { it.userId } doReturn authorId
            on { it.title } doReturn title
            on { it.body } doReturn body
        }

        // When
        val post = cut.mapToDomain(postDto)

        // Then
        val expected = Post(id, authorId, title, body)
        assertEquals(
            expected,
            post
        )
    }

    @Test
    fun `given post dto list when mapToDomain then correct domain model list returned`() {
        // Given
        val id = 1
        val authorId = 2
        val title = "title"
        val body = "body"
        val postDto = mock<PostDto> {
            on { it.id } doReturn id
            on { it.userId } doReturn authorId
            on { it.title } doReturn title
            on { it.body } doReturn body
        }

        // When
        val post = cut.mapToDomain(listOf(postDto, postDto))

        // Then
        val expected = Post(id, authorId, title, body)
        assertEquals(
            listOf(expected, expected),
            post
        )
    }
}