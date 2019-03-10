package dev.dvdciri.sampleapp.postdetails.model

import dev.dvdciri.sampleapp.ui.ItemUiModel

data class CommentUiModel(
    override val id: String,
    val content:String,
    val title: String,
    val avatarUrl: String
): ItemUiModel