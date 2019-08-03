package dev.dvdciri.sampleapp.data.source

import dev.dvdciri.sampleapp.data.model.CommentDto
import dev.dvdciri.sampleapp.data.model.PostDto
import dev.dvdciri.sampleapp.data.model.UserDto
import io.reactivex.Single


class JsonPlaceholderNetworkDataSource
constructor(
    private val jsonPlaceholderClient: JsonPlaceholderClient
){

    fun getPostDtoList(): Single<List<PostDto>> {
        return jsonPlaceholderClient.getPostDtoList()
    }

    fun getPostDto(postId: Int): Single<PostDto> {
        return jsonPlaceholderClient.getPostDto(postId)
    }

    fun getUserDto(userId: Int): Single<UserDto> {
        return jsonPlaceholderClient.getUserDto(userId)
    }

    fun getCommentDtoList(postId: Int): Single<List<CommentDto>> {
        return jsonPlaceholderClient.getCommentDtoList(postId)
    }
}