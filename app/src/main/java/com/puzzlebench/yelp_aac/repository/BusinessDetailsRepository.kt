package com.puzzlebench.yelp_aac.repository

import androidx.lifecycle.LiveData
import com.puzzlebench.yelp_aac.presentation.list.Business
import com.puzzlebench.yelp_aac.repository.model.BusinessDetailsState

interface BusinessDetailsRepository {
    suspend fun getBusinessDetailsById(businessId: String): BusinessDetailsState
    fun getBusinessById(businessId: String): LiveData<Business>

}