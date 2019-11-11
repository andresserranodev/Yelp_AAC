package com.puzzlebench.yelp_aac.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.puzzlebench.yelp_aac.R
import com.puzzlebench.yelp_aac.databinding.DetailsBusinessFragmentBinding
import com.puzzlebench.yelp_aac.presentation.adapter.CategoriesAdapter
import com.puzzlebench.yelp_aac.presentation.adapter.PhotosAdapter
import com.puzzlebench.yelp_aac.presentation.di.ViewModelInjector
import com.puzzlebench.yelp_aac.presentation.android.YelpApplication
import com.puzzlebench.yelp_aac.presentation.viewmodel.DetailsBusinessViewModel
import kotlinx.android.synthetic.main.details_business_fragment.*

class DetailsBusinessFragment : Fragment() {

    private val args: DetailsBusinessFragmentArgs by navArgs()

    private val detailsBusinessViewModel: DetailsBusinessViewModel by viewModels {
        ViewModelInjector.provideDetailsBusinessViewModelFactory(
            (requireContext().applicationContext as YelpApplication).businessDetailsRepository,
            args.businessId
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<DetailsBusinessFragmentBinding>(
            inflater, R.layout.details_business_fragment, container, false
        ).apply {
            viewModel = detailsBusinessViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        val categoriesAdapter = CategoriesAdapter()
        binding.rvCategories.apply {
            adapter = categoriesAdapter
        }
        val photosAdapter = PhotosAdapter()
        binding.rvPhotos.apply {
            adapter = photosAdapter
        }
        subscribeViewModel(categoriesAdapter, photosAdapter)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsBusinessViewModel.getBusinessDetails()
        coordinateMotion()
    }

    private fun coordinateMotion() {
        val listener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val seekPosition = -verticalOffset / appbar_layout.totalScrollRange.toFloat()
            motion_layout.progress = seekPosition
        }
        appbar_layout.addOnOffsetChangedListener(listener)
    }

    private fun subscribeViewModel(
        categoriesAdapter: CategoriesAdapter,
        photosAdapter: PhotosAdapter
    ) {
        detailsBusinessViewModel.businessCategories.observe(viewLifecycleOwner) {
            categoriesAdapter.submitList(it)
            categories_progressBar.visibility = View.GONE
        }
        detailsBusinessViewModel.businessPhotos.observe(viewLifecycleOwner) {
            photosAdapter.submitList(it)
            photos_progressBar.visibility = View.GONE
        }
        detailsBusinessViewModel.errorFetchingData.observe(viewLifecycleOwner) {
            displayErrorMessage(it)
        }
    }

    private fun displayErrorMessage(error: String) {

        activity?.let {
            Snackbar.make(
                it.findViewById(android.R.id.content),
                getString(R.string.network_error, error),
                Snackbar.LENGTH_INDEFINITE
            )
                .setAction(getString(R.string.retry)) {
                    detailsBusinessViewModel.getBusinessDetails()
                }.show()
        }

    }
}
