package com.puzzlebench.yelp_aac.data.remote

import com.puzzlebench.yelp_aac.presentation.BusinessDetailsState

interface RemoteFetchBusinessDetailsById {
    suspend fun fetchBusinessDetailsById(businessId: String): BusinessDetailsState
}