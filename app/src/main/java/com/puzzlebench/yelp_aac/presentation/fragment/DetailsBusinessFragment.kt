package com.puzzlebench.yelp_aac.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.puzzlebench.yelp_aac.R
import kotlinx.android.synthetic.main.details_business_fragment.*

class DetailsBusinessFragment : Fragment() {

    private val args: DetailsBusinessFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_business_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_category_list.text = args.businessId

    }

}
