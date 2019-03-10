package dev.dvdciri.sampleapp.postdetails.model

import dev.dvdciri.sampleapp.ui.ItemUiModel

data class CommentInfoUiModel(
    override val id: String,
    val commentNumberText: String
): ItemUiModel