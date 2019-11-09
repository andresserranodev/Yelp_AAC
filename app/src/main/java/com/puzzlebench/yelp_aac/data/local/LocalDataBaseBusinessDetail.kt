package com.puzzlebench.yelp_aac.data.local

import com.puzzlebench.yelp_aac.repository.model.BusinessDetails
import com.puzzlebench.yelp_aac.repository.model.BusinessDetailsState

interface LocalDataBaseBusinessDetail {

    suspend fun getBusinessDetailsByBusinessId(businessId: String): BusinessDetailsState

    suspend fun insertBusinessDetails(business: BusinessDetails)

    suspend fun deleteAllBusinessDetails()
}
