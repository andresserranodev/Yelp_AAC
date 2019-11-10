package com.puzzlebench.yelp_aac.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.AppBarLayout
import com.puzzlebench.yelp_aac.R
import com.puzzlebench.yelp_aac.databinding.DetailsBusinessFragmentBinding
import com.puzzlebench.yelp_aac.presentation.ViewModelInjector
import com.puzzlebench.yelp_aac.presentation.YelpApplication
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
}
