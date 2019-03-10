package dev.dvdciri.sampleapp.postdetails.mapper

import android.content.res.Resources
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import dev.dvdciri.sampleapp.R
import dev.dvdciri.sampleapp.domain.model.Comment
import dev.dvdciri.sampleapp.framework.mapper.ItemUiModelIdGenerator
import dev.dvdciri.sampleapp.postdetails.model.CommentInfoUiModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class CommentListToCommentInfoUiModelMapperTest {

    private lateinit var cut: CommentListToCommentInfoUiModelMapper

    @Mock private lateinit var itemUiModelIdGenerator: ItemUiModelIdGenerator
    @Mock private lateinit var resources: Resources

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = CommentListToCommentInfoUiModelMapper(itemUiModelIdGenerator, resources)
    }

    @Test
    fun `given list of comments when mapToPresentation then correct ui model returned`() {
        // Given
        val commentList = listOf<Comment>(mock(), mock(), mock(), mock(), mock(), mock())
        val id = "an id"
        whenever(itemUiModelIdGenerator.generateId()).thenReturn(id)

        whenever(resources.getString(R.string.comments_text)).thenReturn("Comments %d")

        // When
        val commentInfoUiModel = cut.mapToPresentation(commentList)

        // Then
        val expected = CommentInfoUiModel(id, "Comments 6")
        assertEquals(expected, commentInfoUiModel)
    }
}