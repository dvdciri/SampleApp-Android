package dev.dvdciri.sampleapp.data

import dev.dvdciri.sampleapp.data.mapper.CommentDtoToCommentMapper
import dev.dvdciri.sampleapp.data.mapper.PostDtoToPostMapper
import dev.dvdciri.sampleapp.data.source.JsonPlaceholderNetworkDataSource
import dev.dvdciri.sampleapp.domain.model.Comment
import dev.dvdciri.sampleapp.domain.model.Post
import dev.dvdciri.sampleapp.domain.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

class PostRepositoryImpl
@Inject constructor(
    private val jsonPlaceholderNetworkDataSource: JsonPlaceholderNetworkDataSource,
    private val postDtoToPostMapper: PostDtoToPostMapper,
    private val commentDtoToCommentMapper: CommentDtoToCommentMapper
) : PostRepository {

    override fun getPosts(): Single<List<Post>> {
        return jsonPlaceholderNetworkDataSource.getPostDtoList()
            .map(postDtoToPostMapper::mapToDomain)
    }

    override fun getPost(postId: Int): Single<Post> {
        return jsonPlaceholderNetworkDataSource.getPostDto(postId)
            .map(postDtoToPostMapper::mapToDomain)
    }

    override fun getComments(postId: Int): Single<List<Comment>> {
        return jsonPlaceholderNetworkDataSource.getCommentDtoList(postId)
            .map(commentDtoToCommentMapper::mapToDomain)
    }
}