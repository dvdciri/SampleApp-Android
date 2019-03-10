package dev.dvdciri.sampleapp.post.mapper

import dev.dvdciri.sampleapp.domain.model.Post
import dev.dvdciri.sampleapp.post.model.PostUiModel
import javax.inject.Inject

class PostToPostUiModelMapper @Inject constructor(){

    fun mapToPresentation(postList: List<Post>): List<PostUiModel> {
        return postList.map(this::mapToPresentation)
    }

    fun mapToPresentation(post: Post): PostUiModel {
        return with(post) {
            PostUiModel(
                id = id.toString(),
                title = title.capitalize(),
                content = body.capitalize()
            )
        }
    }
}