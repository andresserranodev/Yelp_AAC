package com.puzzlebench.yelp_aac.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.puzzlebench.yelp_aac.R
import com.puzzlebench.yelp_aac.presentation.ViewModelInjector
import com.puzzlebench.yelp_aac.presentation.YelpApplication
import com.puzzlebench.yelp_aac.presentation.viewmodel.ListBusinessViewModel
import kotlinx.android.synthetic.main.list_business_fragment.*

class ListBusinessFragment : Fragment() {


    private val viewModel: ListBusinessViewModel by viewModels {
        ViewModelInjector.provideListBusinessViewModelFactory(
            (requireContext().applicationContext as YelpApplication).businessRepository
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBussines()
        viewModel.business.observeForever {
            tv_business_list.text = it
        }
        tv_business_list.setOnClickListener {
            val businessId = "KeNGoOn5jsAtsq-AXyDWzQ"
            val action =
                ListBusinessFragmentDirections.actionMainFragmentToDetailsBusinessFragment()
            action.businessId = businessId
            Navigation.findNavController(view).navigate(action)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.list_business_fragment, container, false)
    }
}
