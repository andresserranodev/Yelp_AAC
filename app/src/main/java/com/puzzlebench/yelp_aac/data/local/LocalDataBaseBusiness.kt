package com.puzzlebench.yelp_aac.data.local

import com.puzzlebench.yelp_aac.presentation.model.Business
import com.puzzlebench.yelp_aac.repository.model.BusinessState

interface LocalDataBaseBusiness {
    suspend fun getBusiness(): BusinessState
    suspend fun saveBusiness(business: Business)
    fun saveAll(business: List<Business>)
    fun getBusinessSize() : Int
    suspend fun deleteAll()
}
