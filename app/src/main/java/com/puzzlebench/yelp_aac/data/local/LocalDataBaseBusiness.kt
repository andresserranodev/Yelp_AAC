package com.puzzlebench.yelp_aac.data.local

import com.puzzlebench.yelp_aac.presentation.model.Business
import com.puzzlebench.yelp_aac.presentation.model.BussinesState

interface LocalDataBaseBusiness {
    suspend fun getBusiness(): BussinesState
    suspend fun saveBusiness(business: Business)
    suspend fun deleteAll()
}