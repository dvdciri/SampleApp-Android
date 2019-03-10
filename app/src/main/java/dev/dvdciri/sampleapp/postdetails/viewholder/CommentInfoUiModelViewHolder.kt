package dev.dvdciri.sampleapp.postdetails.viewholder

import android.view.View
import dev.dvdciri.sampleapp.postdetails.model.CommentInfoUiModel
import dev.dvdciri.sampleapp.ui.ItemUiModelViewHolder
import kotlinx.android.synthetic.main.comments_info_layout.view.*

class CommentInfoUiModelViewHolder(itemView: View) : ItemUiModelViewHolder<CommentInfoUiModel>(itemView) {

    override fun onBind(itemUiModel: CommentInfoUiModel) {
        itemView.commentNumberTextView.text = itemUiModel.commentNumberText
    }
}