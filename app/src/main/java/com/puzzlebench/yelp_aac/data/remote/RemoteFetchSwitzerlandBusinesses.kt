package com.puzzlebench.yelp_aac.data.remote

import com.puzzlebench.yelp_aac.presentation.model.BussinesState

interface RemoteFetchSwitzerlandBusinesses {
    suspend fun fetchSwitzerlandBusiness(): BussinesState
}
