package dev.dvdciri.sampleapp.postdetails.mapper

import dev.dvdciri.sampleapp.domain.model.Comment
import dev.dvdciri.sampleapp.postdetails.model.CommentUiModel
import javax.inject.Inject

private const val AVATAR_URL_TEMPLATE = "https://api.adorable.io/avatars/180/%d.png"

class CommentToCommentUiModelMapper @Inject constructor() {

    fun mapToPresentation(comment: Comment): CommentUiModel {
        return with(comment) {
            CommentUiModel(
                id = id.toString(),
                title = email,
                content = body,
                avatarUrl = AVATAR_URL_TEMPLATE.format(id)
            )
        }
    }

    fun mapToPresentation(commentList: List<Comment>): List<CommentUiModel> {
        return commentList.map(this::mapToPresentation)
    }
}