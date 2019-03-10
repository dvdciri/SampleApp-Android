package dev.dvdciri.sampleapp.domain.usecase

import dev.dvdciri.sampleapp.domain.model.Comment
import dev.dvdciri.sampleapp.domain.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCommentsByPostIdUseCase
@Inject constructor(
    private val postRepository: PostRepository
) {
    fun buildUseCase(postId: Int): Single<List<Comment>> {
        return postRepository.getComments(postId)
    }
}