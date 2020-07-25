package com.puzzlebench.yelp_aac.data.remote

import com.puzzlebench.yelp_aac.repository.model.BusinessState

interface RemoteFetchBusinessesByLocal {
    suspend fun fetchBusinessByLocation(local: String): BusinessState
}
