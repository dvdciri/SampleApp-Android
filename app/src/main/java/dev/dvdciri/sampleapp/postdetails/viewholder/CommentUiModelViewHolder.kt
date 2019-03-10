package dev.dvdciri.sampleapp.postdetails.viewholder

import android.view.View
import com.squareup.picasso.Picasso
import dev.dvdciri.sampleapp.postdetails.model.CommentUiModel
import dev.dvdciri.sampleapp.ui.ItemUiModelViewHolder
import kotlinx.android.synthetic.main.comment_layout_view.view.*

class CommentUiModelViewHolder(itemView: View) : ItemUiModelViewHolder<CommentUiModel>(itemView) {

    override fun onBind(itemUiModel: CommentUiModel) {
        itemView.title.text = itemUiModel.title
        itemView.content.text = itemUiModel.content

        Picasso.get()
            .load(itemUiModel.avatarUrl)
            .into(itemView.avatarImageView)
    }
}