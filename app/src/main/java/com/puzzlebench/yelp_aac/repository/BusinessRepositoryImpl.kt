package com.puzzlebench.yelp_aac.repository

import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchSwitzerlandBusinesses
import com.puzzlebench.yelp_aac.repository.model.BusinessState

class BusinessRepositoryImpl constructor(
    private val remoteFetchSwitzerlandBusinesses: RemoteFetchSwitzerlandBusinesses,
    private val localDataBaseBusiness: LocalDataBaseBusiness
) : BusinessRepository {
    override suspend fun getBusiness(): BusinessState {
        val localBusiness = localDataBaseBusiness.getBusiness()
        return if (localBusiness.businesses.isEmpty()) {
            val remoteBusiness = remoteFetchSwitzerlandBusinesses.fetchSwitzerlandBusiness()
            if (remoteBusiness.error.isEmpty()) {
                remoteBusiness.businesses.map {
                    localDataBaseBusiness.saveBusiness(it)
                }
            }
            remoteBusiness
        } else {
            localBusiness
        }
    }
}