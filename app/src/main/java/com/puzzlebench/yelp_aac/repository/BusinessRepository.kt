package com.puzzlebench.yelp_aac.repository

import com.puzzlebench.yelp_aac.repository.model.BussinesState

interface BusinessRepository {
    suspend fun getBusiness(): BussinesState
}