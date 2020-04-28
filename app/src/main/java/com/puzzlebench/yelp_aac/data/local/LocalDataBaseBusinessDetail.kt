package com.puzzlebench.yelp_aac.data.local

import androidx.lifecycle.LiveData
import com.puzzlebench.yelp_aac.presentation.model.Business
import com.puzzlebench.yelp_aac.presentation.model.BusinessDetails
import com.puzzlebench.yelp_aac.repository.model.BusinessDetailsState

interface LocalDataBaseBusinessDetail {

    suspend fun getBusinessDetailsByBusinessId(businessId: String): BusinessDetailsState

    suspend fun insertBusinessDetails(business: BusinessDetails)

    suspend fun deleteAllBusinessDetails()

    fun getBusinessById(businessId: String): LiveData<Business>
}
