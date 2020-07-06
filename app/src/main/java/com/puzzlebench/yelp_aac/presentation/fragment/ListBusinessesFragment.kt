package com.puzzlebench.yelp_aac.presentation.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.snackbar.Snackbar
import com.puzzlebench.yelp_aac.R
import com.puzzlebench.yelp_aac.databinding.ListBusinessFragmentBinding
import com.puzzlebench.yelp_aac.presentation.adapter.BusinessAdapter
import com.puzzlebench.yelp_aac.presentation.android.YelpApplication
import com.puzzlebench.yelp_aac.presentation.di.ViewModelInjector
import com.puzzlebench.yelp_aac.presentation.viewmodel.state.BusinessViewState
import com.puzzlebench.yelp_aac.presentation.viewmodel.ListBusinessesViewModel
import kotlinx.android.synthetic.main.list_business_fragment.*

class ListBusinessesFragment : Fragment() {

    private lateinit var binding: ListBusinessFragmentBinding
    private val viewModel: ListBusinessesViewModel by viewModels {
        ViewModelInjector.provideListBusinessViewModelFactory(
            (requireContext().applicationContext as YelpApplication).businessRepository
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.show()
        }
        viewModel.getBusiness()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController()) ||
                super.onOptionsItemSelected(item)
    }

    private fun onViewState(
        businessViewState: BusinessViewState,
        businessAdapter: BusinessAdapter
    ) {
        when (businessViewState) {
            is BusinessViewState.ShowBusiness -> {
                businessAdapter.submitList(businessViewState.business)
                progressBar.visibility = View.GONE
            }
            is BusinessViewState.ShowErrorMessage -> {
                displayErrorMessage(businessViewState.message)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListBusinessFragmentBinding.inflate(inflater, container, false)
        val businessAdapter = BusinessAdapter()
        binding.businessListRv.apply {
            adapter = businessAdapter
        }
        viewModel.businessState.observe(viewLifecycleOwner) {
            onViewState(it, businessAdapter)
        }
        return binding.root
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
