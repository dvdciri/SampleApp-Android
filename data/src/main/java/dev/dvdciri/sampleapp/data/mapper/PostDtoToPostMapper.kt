package dev.dvdciri.sampleapp.data.mapper

import dev.dvdciri.sampleapp.data.model.PostDto
import dev.dvdciri.sampleapp.domain.model.Post
import javax.inject.Inject

class PostDtoToPostMapper @Inject constructor() {

    fun mapToDomain(postDto: PostDto): Post {
        return with(postDto) {
            Post(
                id = id,
                authorId = userId,
                title = title,
                body = body
            )
        }
    }

    fun mapToDomain(dtoList: List<PostDto>): List<Post> = dtoList.map(this::mapToDomain)
}