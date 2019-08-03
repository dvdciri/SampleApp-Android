package dev.dvdciri.sampleapp.postdetails.mapper

import dev.dvdciri.sampleapp.domain.model.PostDetails
import dev.dvdciri.sampleapp.post.mapper.PostToPostUiModelMapper
import dev.dvdciri.sampleapp.ui.ItemUiModel


class PostDetailsToUiModelsMapper
constructor(
    private val userToAuthorUiModelMapper: UserToAuthorUiModelMapper,
    private val postToPostUiModelMapper: PostToPostUiModelMapper
) {

    fun mapToPresentation(postDetails: PostDetails): List<ItemUiModel> {
        return listOf(
            postToPostUiModelMapper.mapToPresentation(postDetails.post),
            userToAuthorUiModelMapper.mapToPresentation(postDetails.author)
        )
    }
}