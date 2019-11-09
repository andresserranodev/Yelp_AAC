package com.puzzlebench.yelp_aac.data.remote

import com.puzzlebench.yelp_aac.presentation.BussinesState

interface RemoteFetchSwitzerlandBusinesses {
    suspend fun fetchSwitzerlandBusiness(): BussinesState

}