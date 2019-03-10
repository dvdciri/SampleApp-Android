package dev.dvdciri.sampleapp.postdetails.mapper

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import dev.dvdciri.sampleapp.domain.model.Comment
import dev.dvdciri.sampleapp.postdetails.model.CommentInfoUiModel
import dev.dvdciri.sampleapp.postdetails.model.CommentUiModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class CommentListToItemUiModelMapperTest {

    private lateinit var cut: CommentListToItemUiModelMapper

    @Mock
    private lateinit var commentToCommentUiModelMapper: CommentToCommentUiModelMapper
    @Mock
    private lateinit var commentListToCommentInfoUiModelMapper: CommentListToCommentInfoUiModelMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = CommentListToItemUiModelMapper(commentToCommentUiModelMapper, commentListToCommentInfoUiModelMapper)
    }

    @Test
    fun `given comment list when mapToPresentation then correct ui models returned`() {
        // Given
        val commentList = listOf<Comment>(mock(), mock())

        val commentUiModelList = listOf<CommentUiModel>(mock(), mock())
        whenever(commentToCommentUiModelMapper.mapToPresentation(commentList)).thenReturn(commentUiModelList)

        val commentInfoUiModel = mock<CommentInfoUiModel>()
        whenever(commentListToCommentInfoUiModelMapper.mapToPresentation(commentList)).thenReturn(commentInfoUiModel)

        // When
        val itemUiModelList = cut.mapToPresentation(commentList)

        // Then
        val expected = listOf(commentInfoUiModel) + commentUiModelList
        assertEquals(expected, itemUiModelList)
    }
}