package com.puzzlebench.yelp_aac.data.remote

import com.puzzlebench.yelp_aac.repository.model.BusinessState

interface RemoteFetchBusinessesByLocale {
    suspend fun fetchBusinessByLocation(locale: String): BusinessState
}
