package com.puzzlebench.yelp_aac.repository

import com.puzzlebench.yelp_aac.repository.model.BusinessDetailsState

interface BusinessDetailsRepository {
    suspend fun getBusinessDetailsById(businessId: String): BusinessDetailsState
}