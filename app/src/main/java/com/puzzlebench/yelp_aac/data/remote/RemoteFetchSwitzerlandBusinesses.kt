package com.puzzlebench.yelp_aac.data.remote

import com.puzzlebench.yelp_aac.repository.model.BussinesState

interface RemoteFetchSwitzerlandBusinesses {
    suspend fun fetchSwitzerlandBusiness(): BussinesState
}
