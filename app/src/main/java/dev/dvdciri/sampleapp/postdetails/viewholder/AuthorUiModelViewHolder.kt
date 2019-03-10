package dev.dvdciri.sampleapp.postdetails.viewholder

import android.view.View
import com.squareup.picasso.Picasso
import dev.dvdciri.sampleapp.postdetails.model.AuthorUiModel
import dev.dvdciri.sampleapp.ui.ItemUiModelViewHolder
import kotlinx.android.synthetic.main.author_layout_view.view.*

class AuthorUiModelViewHolder(itemView: View) : ItemUiModelViewHolder<AuthorUiModel>(itemView) {

    override fun onBind(itemUiModel: AuthorUiModel) {
        itemView.title.text = itemUiModel.titleText
        itemView.email.text = itemUiModel.emailText

        Picasso.get()
            .load(itemUiModel.avatarUrl)
            .into(itemView.avatarImageView)
    }
}