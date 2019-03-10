package dev.dvdciri.sampleapp.postdetails

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.squareup.picasso.Picasso
import dev.dvdciri.sampleapp.R
import dev.dvdciri.sampleapp.di.ApplicationComponentHolder
import dev.dvdciri.sampleapp.framework.extensions.observe
import dev.dvdciri.sampleapp.framework.extensions.viewModel
import dev.dvdciri.sampleapp.framework.viewmodel.viewstate.ErrorUiModel
import dev.dvdciri.sampleapp.ui.ItemUiModel
import dev.dvdciri.sampleapp.ui.RecyclerViewItemUiModelAdapter
import kotlinx.android.synthetic.main.post_details_activity.*
import javax.inject.Inject

class PostDetailsActivity : AppCompatActivity() {

    private lateinit var postDetailsViewModel: PostDetailsViewModel
    private lateinit var itemUiModelAdapter: RecyclerViewItemUiModelAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_details_activity)

        toolbar.setNavigationOnClickListener { onBackPressed() }

        ApplicationComponentHolder.get(application).inject(this)

        itemUiModelAdapter = RecyclerViewItemUiModelAdapter()
        recyclerView.adapter = itemUiModelAdapter

        val detailsNavigationParams = intent.getSerializableExtra(PARAM_POST_ID) as DetailsNavigationParams

        postDetailsViewModel = viewModel(viewModelFactory) {
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
