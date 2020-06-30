package com.puzzlebench.yelp_aac.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.puzzlebench.yelp_aac.repository.BusinessRepository

@Suppress("UNCHECKED_CAST")
class ListBusinessesViewModelFactory(private val businessRepository: BusinessRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        ListBusinessesViewModel(
            businessRepository
        ) as T
}
