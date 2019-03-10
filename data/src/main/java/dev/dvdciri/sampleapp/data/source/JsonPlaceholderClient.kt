package dev.dvdciri.sampleapp.data.source

import dev.dvdciri.sampleapp.data.model.CommentDto
import dev.dvdciri.sampleapp.data.model.PostDto
import dev.dvdciri.sampleapp.data.model.UserDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPlaceholderClient {

    @GET("/posts")
    fun getPostDtoList(): Single<List<PostDto>>

    @GET("/posts/{postId}")
    fun getPostDto(@Path("postId") postId: Int): Single<PostDto>

    @GET("/users/{userId}")
    fun getUserDto(@Path("userId") userId: Int): Single<UserDto>

    @GET("/comments")
    fun getCommentDtoList(@Query("postId") postId: Int): Single<List<CommentDto>>
}