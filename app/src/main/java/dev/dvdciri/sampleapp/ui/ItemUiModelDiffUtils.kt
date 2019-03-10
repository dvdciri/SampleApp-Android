package dev.dvdciri.sampleapp.ui

import android.support.v7.util.DiffUtil

class ItemUiModelDiffUtils(
    private val oldItems: List<ItemUiModel>,
    private val newItems: List<ItemUiModel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldItems[oldPosition].id == newItems[newPosition].id
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldItems[oldPosition] == newItems[newPosition]
    }

}