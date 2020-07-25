package com.puzzlebench.yelp_aac.repository

import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessesByLocation
import com.puzzlebench.yelp_aac.repository.model.BusinessState

class BusinessRepositoryImpl constructor(
    private val remoteFetchBusinessesByLocation: RemoteFetchBusinessesByLocation,
    private val localDataBaseBusiness: LocalDataBaseBusiness
) : BusinessRepository {
    override suspend fun getBusiness(location :String): BusinessState {
        val localBusiness = localDataBaseBusiness.getBusiness()
        return if (localBusiness.businesses.isEmpty()) {
            val remoteBusiness = remoteFetchBusinessesByLocation.fetchBusinessByLocation(location)
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
