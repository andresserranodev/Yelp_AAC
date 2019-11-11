package com.puzzlebench.yelp_aac.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.puzzlebench.yelp_aac.R
import com.puzzlebench.yelp_aac.databinding.ListBusinessFragmentBinding
import com.puzzlebench.yelp_aac.presentation.adapter.BusinessAdapter
import com.puzzlebench.yelp_aac.presentation.di.ViewModelInjector
import com.puzzlebench.yelp_aac.presentation.android.YelpApplication
import com.puzzlebench.yelp_aac.presentation.viewmodel.ListBusinessesViewModel
import kotlinx.android.synthetic.main.list_business_fragment.*
import org.jetbrains.anko.toast

class ListBusinessesFragment : Fragment() {


    private lateinit var binding: ListBusinessFragmentBinding
    private val viewModel: ListBusinessesViewModel by viewModels {
        ViewModelInjector.provideListBusinessViewModelFactory(
            (requireContext().applicationContext as YelpApplication).businessRepository
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBusiness()
        viewModel.businessError.observeForever {
            displayErrorMessage(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListBusinessFragmentBinding.inflate(inflater, container, false)
        val businessAdapter = BusinessAdapter()
        binding.businessListRv.apply {
            adapter = businessAdapter
        }
        subscribeViewModel(businessAdapter)
        return binding.root
    }

    private fun subscribeViewModel(businessAdapter: BusinessAdapter) {
        viewModel.business.observe(viewLifecycleOwner) {
            businessAdapter.submitList(it)
            progressBar.visibility = View.GONE
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
                    viewModel.getBusiness()
                }.show()
        }

    }
}
