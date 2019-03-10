package dev.dvdciri.sampleapp.post.viewholder

import android.view.View
import dev.dvdciri.sampleapp.post.model.PostUiModel
import dev.dvdciri.sampleapp.ui.ItemUiModelViewHolder
import dev.dvdciri.sampleapp.ui.RecyclerViewItemUiModelAdapter
import kotlinx.android.synthetic.main.post_layout_view.view.*

class PostUiModelViewHolder(
    itemView: View,
    private val itemClickListener: RecyclerViewItemUiModelAdapter.OnItemClickListener
) : ItemUiModelViewHolder<PostUiModel>(itemView) {

    override fun onBind(itemUiModel: PostUiModel) {
        itemView.title.text = itemUiModel.title
        itemView.content.text = itemUiModel.content
        itemView.setOnClickListener { itemClickListener.onItemClicked(adapterPosition) }
    }
}