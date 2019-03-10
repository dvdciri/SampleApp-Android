package dev.dvdciri.sampleapp.postdetails.model

import dev.dvdciri.sampleapp.ui.ItemUiModel

data class AuthorUiModel(
    override val id: String,
    val titleText: String,
    val emailText: String,
    val avatarUrl: String
): ItemUiModel