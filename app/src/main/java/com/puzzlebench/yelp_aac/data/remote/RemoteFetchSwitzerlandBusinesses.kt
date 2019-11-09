package com.puzzlebench.yelp_aac.data.remote

import com.puzzlebench.yelp_aac.repository.model.BusinessState

interface RemoteFetchSwitzerlandBusinesses {
    suspend fun fetchSwitzerlandBusiness(): BusinessState
}
