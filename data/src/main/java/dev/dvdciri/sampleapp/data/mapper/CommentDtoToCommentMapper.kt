package dev.dvdciri.sampleapp.data.mapper

import dev.dvdciri.sampleapp.data.model.CommentDto
import dev.dvdciri.sampleapp.domain.model.Comment


class CommentDtoToCommentMapper constructor() {

    fun mapToDomain(commentDtoList: List<CommentDto>): List<Comment> = commentDtoList.map(this::mapToDomain)

    private fun mapToDomain(commentDto: CommentDto): Comment {
        return with(commentDto) {
            Comment(
                id = id,
                email = email,
                body = body
            )
        }
    }
}