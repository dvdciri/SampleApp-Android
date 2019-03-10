package dev.dvdciri.sampleapp.ui

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dev.dvdciri.sampleapp.R
import dev.dvdciri.sampleapp.post.model.PostUiModel
import dev.dvdciri.sampleapp.post.viewholder.PostUiModelViewHolder
import dev.dvdciri.sampleapp.postdetails.model.AuthorUiModel
import dev.dvdciri.sampleapp.postdetails.model.CommentInfoUiModel
import dev.dvdciri.sampleapp.postdetails.model.CommentUiModel
import dev.dvdciri.sampleapp.postdetails.viewholder.AuthorUiModelViewHolder
import dev.dvdciri.sampleapp.postdetails.viewholder.CommentInfoUiModelViewHolder
import dev.dvdciri.sampleapp.postdetails.viewholder.CommentUiModelViewHolder
import dev.dvdciri.sampleapp.ui.skeleton.SkeletonItemUiModel
import dev.dvdciri.sampleapp.ui.skeleton.SkeletonViewHolder

class RecyclerViewItemUiModelAdapter(
    private val onItemClickListener: OnItemClickListener = EmptyOnItemClickListener()
) : RecyclerView.Adapter<ItemUiModelViewHolder<ItemUiModel>>() {

    private val itemUiModelList: MutableList<ItemUiModel> by lazy { mutableListOf<ItemUiModel>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemUiModelViewHolder<ItemUiModel> {
        val adapter = when (viewType) {
            ITEM_TYPE_POST -> PostUiModelViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_layout_view, parent, false), onItemClickListener)
            ITEM_TYPE_SKELETON -> SkeletonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.skeleton_layout_view, parent, false))
            ITEM_TYPE_COMMENT -> CommentUiModelViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comment_layout_view, parent, false))
            ITEM_TYPE_AUTHOR -> AuthorUiModelViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.author_layout_view, parent, false))
            ITEM_TYPE_COMMENT_INFO -> CommentInfoUiModelViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comments_info_layout, parent, false))
            else -> throw IllegalStateException("Item type $viewType not handled")
        }

        return adapter as ItemUiModelViewHolder<ItemUiModel>
    }

    override fun getItemCount(): Int {
        return itemUiModelList.size
    }

    override fun onBindViewHolder(viewHolder: ItemUiModelViewHolder<ItemUiModel>, position: Int) {
        viewHolder.onBind(itemUiModelList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemUiModelList[position]) {
            is PostUiModel -> ITEM_TYPE_POST
            is SkeletonItemUiModel -> ITEM_TYPE_SKELETON
            is CommentUiModel -> ITEM_TYPE_COMMENT
            is AuthorUiModel -> ITEM_TYPE_AUTHOR
            is CommentInfoUiModel -> ITEM_TYPE_COMMENT_INFO
            else -> throw IllegalStateException("Item not handled")
        }
    }

    fun updateItems(newItemUiModels: List<ItemUiModel>) {
        val diffResult = DiffUtil.calculateDiff(ItemUiModelDiffUtils(itemUiModelList, newItemUiModels))

        this.itemUiModelList.clear()
        this.itemUiModelList.addAll(newItemUiModels)

        diffResult.dispatchUpdatesTo(this)
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }

    companion object {
        private const val ITEM_TYPE_POST = 0
        private const val ITEM_TYPE_COMMENT = 1
        private const val ITEM_TYPE_AUTHOR = 2
        private const val ITEM_TYPE_SKELETON = 3
        private const val ITEM_TYPE_COMMENT_INFO = 4
    }
}