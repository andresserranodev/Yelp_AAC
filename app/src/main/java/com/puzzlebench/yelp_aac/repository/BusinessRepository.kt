package com.puzzlebench.yelp_aac.repository

import com.puzzlebench.yelp_aac.repository.model.BusinessState

interface BusinessRepository {
    suspend fun getBusiness(local: String): BusinessState
}
