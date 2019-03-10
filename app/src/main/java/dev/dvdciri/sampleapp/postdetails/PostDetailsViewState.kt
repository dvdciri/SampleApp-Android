package dev.dvdciri.sampleapp.postdetails

import dev.dvdciri.sampleapp.framework.viewmodel.viewstate.ErrorUiModel
import dev.dvdciri.sampleapp.ui.ItemUiModel

data class PostDetailsViewState(
    val error: ErrorUiModel,
    val heroImageUrl: String,
    val postUiModels: List<ItemUiModel>
)
