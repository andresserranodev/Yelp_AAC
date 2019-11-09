package com.puzzlebench.yelp_aac.presentation

import com.puzzlebench.yelp_aac.presentation.viewmodel.ListBusinessesViewModelFactory
import com.puzzlebench.yelp_aac.repository.BusinessRepository

object ViewModelInjector {

    fun provideListBusinessViewModelFactory(businessRepository: BusinessRepository) =
        ListBusinessesViewModelFactory(businessRepository)
}