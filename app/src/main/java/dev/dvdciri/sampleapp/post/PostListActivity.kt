package dev.dvdciri.sampleapp.post

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import dev.dvdciri.sampleapp.R
import dev.dvdciri.sampleapp.framework.extensions.observe
import dev.dvdciri.sampleapp.framework.viewmodel.viewstate.ErrorUiModel
import dev.dvdciri.sampleapp.postdetails.DetailsNavigationParams
import dev.dvdciri.sampleapp.postdetails.PostDetailsActivity
import dev.dvdciri.sampleapp.ui.ItemUiModel
import dev.dvdciri.sampleapp.ui.RecyclerViewItemUiModelAdapter
import kotlinx.android.synthetic.main.post_list_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

class PostListActivity : AppCompatActivity(), RecyclerViewItemUiModelAdapter.OnItemClickListener {

    private val postListViewModel: PostListViewModel by viewModel()

    private lateinit var itemUiModelAdapter: RecyclerViewItemUiModelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_list_activity)

        itemUiModelAdapter = RecyclerViewItemUiModelAdapter(this)
        recyclerView.adapter = itemUiModelAdapter

        with (postListViewModel) {
            observe(postListViewState, ::onPageListViewStateChanged)
            observe(navigateToDetails, ::onNavigateToDetailsEvent)
            loadPosts()
        }
    }

    override fun onItemClicked(position: Int) {
        postListViewModel.onItemClicked(position)
    }

    private fun onNavigateToDetailsEvent(detailsNavigationParams: DetailsNavigationParams?) {
        detailsNavigationParams?.let {
            startActivity(PostDetailsActivity.newIntent(this, it))
        }
    }

    private fun onPageListViewStateChanged(postListViewState: PostListViewState?) {
        postListViewState?.let {
            handleDataStateChanged(it.postUiModels)
            handleErrorStateChanged(it.error)
        }
    }

    private fun handleDataStateChanged(itemUiModels: List<ItemUiModel>) {
        itemUiModelAdapter.updateItems(itemUiModels)
    }

    private fun handleErrorStateChanged(error: ErrorUiModel) {
        when (error) {
            is ErrorUiModel.Message -> {
                errorMessage.visibility = View.VISIBLE
                errorMessage.setText(error.message)
            }
            ErrorUiModel.None -> errorMessage.visibility = View.INVISIBLE
        }
    }
}
