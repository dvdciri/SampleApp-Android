package dev.dvdciri.sampleapp.domain.usecase

import dev.dvdciri.sampleapp.domain.model.Post
import dev.dvdciri.sampleapp.domain.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPostsUseCase
@Inject constructor(
    private val postRepository: PostRepository
) {
    fun buildUseCase(): Single<List<Post>> {
        return postRepository.getPosts()
    }
}