package com.puzzlebench.yelp_aac.repository

import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusinessDetail
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessesByLocation

const val DEFAULT_LOCATION = "Switzerland"

class UpdateRepositoryImpl constructor(
    private val remoteFetchBusinessesByLocation: RemoteFetchBusinessesByLocation,
    private val localDataBaseBusiness: LocalDataBaseBusiness,
    private val localDataBaseBusinessDetail: LocalDataBaseBusinessDetail
) : UpdateRepository {
    override suspend fun updateBusiness() {
        val remoteBusiness =
            remoteFetchBusinessesByLocation.fetchBusinessByLocation(DEFAULT_LOCATION)
        if (remoteBusiness.error.isEmpty()) {
            localDataBaseBusiness.deleteAll()
            localDataBaseBusinessDetail.deleteAllBusinessDetails()
            remoteBusiness.businesses.map {
                localDataBaseBusiness.saveBusiness(it)
            }
        }
    }
}
