package dev.dvdciri.sampleapp.domain.repository

import dev.dvdciri.sampleapp.domain.model.Comment
import dev.dvdciri.sampleapp.domain.model.Post
import io.reactivex.Single

interface PostRepository {

    fun getPosts(): Single<List<Post>>

    fun getPost(postId: Int): Single<Post>

    fun getComments(postId: Int): Single<List<Comment>>
}