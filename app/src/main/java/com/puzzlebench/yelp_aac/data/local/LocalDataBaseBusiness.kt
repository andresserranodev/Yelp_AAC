package com.puzzlebench.yelp_aac.data.local

import com.puzzlebench.yelp_aac.repository.model.Business
import com.puzzlebench.yelp_aac.repository.model.BussinesState

interface LocalDataBaseBusiness {
    suspend fun getBusiness(): BussinesState
    suspend fun saveBusiness(business: Business)
    suspend fun deleteAll()
}
