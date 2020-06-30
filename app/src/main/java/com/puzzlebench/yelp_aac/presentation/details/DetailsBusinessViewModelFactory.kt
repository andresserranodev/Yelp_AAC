package com.puzzlebench.yelp_aac.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository

@Suppress("UNCHECKED_CAST")
class DetailsBusinessViewModelFactory(
    private val businessDetailsRepository: BusinessDetailsRepository,
    private val businessId: String
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        DetailsBusinessViewModel(
            businessDetailsRepository,
            businessId
        ) as T
}