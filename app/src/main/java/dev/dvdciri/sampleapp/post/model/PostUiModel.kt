package dev.dvdciri.sampleapp.post.model

import dev.dvdciri.sampleapp.ui.ItemUiModel

data class PostUiModel(
    override val id: String,
    val title: String,
    val content: String
) : ItemUiModel