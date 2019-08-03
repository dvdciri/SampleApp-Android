package dev.dvdciri.sampleapp.postdetails.mapper

import android.content.res.Resources
import dev.dvdciri.sampleapp.R
import dev.dvdciri.sampleapp.domain.model.Comment
import dev.dvdciri.sampleapp.framework.mapper.ItemUiModelIdGenerator
import dev.dvdciri.sampleapp.postdetails.model.CommentInfoUiModel


class CommentListToCommentInfoUiModelMapper
constructor(
    private val itemUiModelIdGenerator: ItemUiModelIdGenerator,
    private val resources: Resources
){

    fun mapToPresentation(commentList: List<Comment>): CommentInfoUiModel {
        return CommentInfoUiModel(
            itemUiModelIdGenerator.generateId(),
            resources.getString(R.string.comments_text).format(commentList.size)
        )
    }
}