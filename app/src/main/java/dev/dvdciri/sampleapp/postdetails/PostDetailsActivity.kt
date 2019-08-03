package dev.dvdciri.sampleapp.postdetails

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.squareup.picasso.Picasso
import dev.dvdciri.sampleapp.R
import dev.dvdciri.sampleapp.framework.extensions.observe
import dev.dvdciri.sampleapp.framework.viewmodel.viewstate.ErrorUiModel
import dev.dvdciri.sampleapp.ui.ItemUiModel
import dev.dvdciri.sampleapp.ui.RecyclerViewItemUiModelAdapter
import kotlinx.android.synthetic.main.post_details_activity.*
import org.koin.android.viewmodel.ext.android.viewModel


class PostDetailsActivity : AppCompatActivity() {

    private val postDetailsViewModel: PostDetailsViewModel by viewModel()
    private lateinit var itemUiModelAdapter: RecyclerViewItemUiModelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_details_activity)

        toolbar.setNavigationOnClickListener { onBackPressed() }

        itemUiModelAdapter = RecyclerViewItemUiModelAdapter()
        recyclerView.adapter = itemUiModelAdapter

        val detailsNavigationParams = intent.getSerializableExtra(PARAM_POST_ID) as DetailsNavigationParams

        with(postDetailsViewModel) {
            observe(postDetailsViewState, ::onPostDetailsViewStateChanged)
            loadPostDetails(detailsNavigationParams)
        }
    }

    private fun onPostDetailsViewStateChanged(postDetailsViewState: PostDetailsViewState?) {
        postDetailsViewState?.let {
            handleDataStateChanged(it.postUiModels, it.heroImageUrl)
            handleErrorStateChanged(it.error)
        }
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

    private fun handleDataStateChanged(uiModels: List<ItemUiModel>, heroImageUrl: String) {
        itemUiModelAdapter.updateItems(uiModels)

        Picasso.get().load(heroImageUrl).into(heroImage)
    }

    companion object {
        private const val PARAM_POST_ID = "post_id"

        fun newIntent(context: Context, detailsNavigationParams: DetailsNavigationParams): Intent {
            return Intent(context, PostDetailsActivity::class.java).apply {
                putExtra(PARAM_POST_ID, detailsNavigationParams)
            }
        }
    }
}
