package com.puzzlebench.yelp_aac.presentation.di

import com.puzzlebench.yelp_aac.presentation.viewmodel.factory.DetailsBusinessViewModelFactory
import com.puzzlebench.yelp_aac.presentation.viewmodel.factory.ListBusinessesViewModelFactory
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository
import com.puzzlebench.yelp_aac.repository.BusinessRepository

object ViewModelInjector {

    fun provideListBusinessViewModelFactory(businessRepository: BusinessRepository) =
        ListBusinessesViewModelFactory(
            businessRepository
        )

    fun provideDetailsBusinessViewModelFactory(
        businessDetailsRepository: BusinessDetailsRepository,
        businessId: String
    ) =
        DetailsBusinessViewModelFactory(
            businessDetailsRepository,
            businessId
        )
}
