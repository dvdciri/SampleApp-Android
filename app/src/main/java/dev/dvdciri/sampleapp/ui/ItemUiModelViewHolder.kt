package dev.dvdciri.sampleapp.ui

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ItemUiModelViewHolder<T : ItemUiModel>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(itemUiModel: T)
}