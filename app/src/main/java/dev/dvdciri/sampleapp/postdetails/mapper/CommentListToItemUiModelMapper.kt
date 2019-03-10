package dev.dvdciri.sampleapp.postdetails.mapper

import dev.dvdciri.sampleapp.domain.model.Comment
import dev.dvdciri.sampleapp.ui.ItemUiModel
import javax.inject.Inject

class CommentListToItemUiModelMapper
@Inject constructor(
    private val commentToCommentUiModelMapper: CommentToCommentUiModelMapper,
    private val commentListToCommentInfoUiModelMapper: CommentListToCommentInfoUiModelMapper
) {

    fun mapToPresentation(commentList: List<Comment>): List<ItemUiModel> {
        val commentUiModels = commentToCommentUiModelMapper.mapToPresentation(commentList)
        val commentInfoUiModel = commentListToCommentInfoUiModelMapper.mapToPresentation(commentList)
        return listOf(commentInfoUiModel) + commentUiModels
    }
}