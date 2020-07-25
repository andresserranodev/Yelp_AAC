package com.puzzlebench.yelp_aac.repository

import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessesByLocal
import com.puzzlebench.yelp_aac.repository.model.BusinessState

class BusinessRepositoryImpl constructor(
    private val remoteFetchBusinessesByLocal: RemoteFetchBusinessesByLocal,
    private val localDataBaseBusiness: LocalDataBaseBusiness
) : BusinessRepository {
    override suspend fun getBusiness(local :String): BusinessState {
        val localBusiness = localDataBaseBusiness.getBusiness()
        return if (localBusiness.businesses.isEmpty()) {
            val remoteBusiness = remoteFetchBusinessesByLocal.fetchBusinessByLocation(local)
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
