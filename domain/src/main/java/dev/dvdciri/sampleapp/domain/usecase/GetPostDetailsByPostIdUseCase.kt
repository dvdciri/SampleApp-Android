package dev.dvdciri.sampleapp.domain.usecase

import dev.dvdciri.sampleapp.domain.model.PostDetails
import dev.dvdciri.sampleapp.domain.repository.PostRepository
import dev.dvdciri.sampleapp.domain.repository.UserRepository
import io.reactivex.Single


class GetPostDetailsByPostIdUseCase
constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) {
    fun buildUseCase(postId: Int): Single<PostDetails> {
        return postRepository.getPost(postId)
            .flatMap { post ->
                userRepository.getUser(post.authorId)
                    .map { user -> PostDetails(post, user) }
            }
    }
}