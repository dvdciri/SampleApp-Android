package dev.dvdciri.sampleapp.post

import dev.dvdciri.sampleapp.framework.viewmodel.viewstate.ErrorUiModel
import dev.dvdciri.sampleapp.ui.ItemUiModel

data class PostListViewState(
    val error: ErrorUiModel,
    val postUiModels: List<ItemUiModel>
)
