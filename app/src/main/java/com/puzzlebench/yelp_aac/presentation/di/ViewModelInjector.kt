package com.puzzlebench.yelp_aac.presentation.di

import com.puzzlebench.yelp_aac.data.local.room.dao.BusinessDao
import com.puzzlebench.yelp_aac.presentation.viewmodel.factory.DetailsBusinessViewModelFactory
import com.puzzlebench.yelp_aac.presentation.viewmodel.factory.ListBusinessesViewModelFactory
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository
import com.puzzlebench.yelp_aac.repository.BusinessRepository
import com.puzzlebench.yelp_aac.repository.FetchBusinessCallback

object ViewModelInjector {

    fun provideListBusinessViewModelFactory(
        fetchBusinessCallback: FetchBusinessCallback,
        businessDao: BusinessDao
    ) =
        ListBusinessesViewModelFactory(
            fetchBusinessCallback,
            businessDao
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
