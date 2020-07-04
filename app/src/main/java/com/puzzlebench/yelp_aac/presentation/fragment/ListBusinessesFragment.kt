package com.puzzlebench.yelp_aac.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.google.android.material.snackbar.Snackbar
import com.puzzlebench.yelp_aac.R
import com.puzzlebench.yelp_aac.data.local.room.entity.BusinessEntity
import com.puzzlebench.yelp_aac.databinding.ListBusinessFragmentBinding
import com.puzzlebench.yelp_aac.presentation.adapter.BusinessAdapter
import com.puzzlebench.yelp_aac.presentation.adapter.BusinessAdapterPager
import com.puzzlebench.yelp_aac.presentation.android.YelpApplication
import com.puzzlebench.yelp_aac.presentation.di.ViewModelInjector
import com.puzzlebench.yelp_aac.presentation.viewmodel.state.BusinessViewState
import com.puzzlebench.yelp_aac.presentation.viewmodel.ListBusinessesViewModel
import kotlinx.android.synthetic.main.list_business_fragment.*

class ListBusinessesFragment : Fragment() {

    private lateinit var binding: ListBusinessFragmentBinding
    private val viewModel: ListBusinessesViewModel by viewModels {
        val app = (requireContext().applicationContext as YelpApplication)
        ViewModelInjector.provideListBusinessViewModelFactory(
            app.provideFetchBusinessCallback,
            app.provideBusinessDao
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListBusinessFragmentBinding.inflate(inflater, container, false)
        val businessAdapterPager = BusinessAdapterPager()

        binding.businessListRv.apply {
            adapter = businessAdapterPager
        }

        viewModel.liveData.observe(
            viewLifecycleOwner,
            Observer<PagedList<BusinessEntity>> { pagedList ->
                businessAdapterPager.submitList(pagedList)
                progressBar.visibility = View.GONE

            })
        return binding.root
    }
}
