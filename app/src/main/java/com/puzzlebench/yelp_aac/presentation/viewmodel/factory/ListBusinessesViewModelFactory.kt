package com.puzzlebench.yelp_aac.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.puzzlebench.yelp_aac.data.local.room.dao.BusinessDao
import com.puzzlebench.yelp_aac.presentation.viewmodel.ListBusinessesViewModel
import com.puzzlebench.yelp_aac.repository.FetchBusinessCallback

@Suppress("UNCHECKED_CAST")
class ListBusinessesViewModelFactory(
    private val fetchBusinessCallback: FetchBusinessCallback,
    private val businessDao: BusinessDao
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        ListBusinessesViewModel(fetchBusinessCallback, businessDao) as T
}
