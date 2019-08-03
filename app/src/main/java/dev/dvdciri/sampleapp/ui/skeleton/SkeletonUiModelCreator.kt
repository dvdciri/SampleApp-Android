package dev.dvdciri.sampleapp.ui.skeleton

import dev.dvdciri.sampleapp.ui.ItemUiModel
import java.util.*


class SkeletonUiModelCreator constructor(){

    fun createUiModels(itemCount: Int): List<ItemUiModel> {
        return (0..itemCount).map {
            SkeletonItemUiModel(UUID.randomUUID().toString())
        }
    }
}