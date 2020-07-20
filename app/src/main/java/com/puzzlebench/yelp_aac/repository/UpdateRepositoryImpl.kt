package com.puzzlebench.yelp_aac.repository

import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusinessDetail
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchSwitzerlandBusinesses

class UpdateRepositoryImpl constructor(
    private val remoteFetchSwitzerlandBusinesses: RemoteFetchSwitzerlandBusinesses,
    private val localDataBaseBusiness: LocalDataBaseBusiness,
    private val localDataBaseBusinessDetail: LocalDataBaseBusinessDetail
) : UpdateRepository {
    override suspend fun updateBusiness() {
        val remoteBusiness = remoteFetchSwitzerlandBusinesses.fetchSwitzerlandBusiness()
        if (remoteBusiness.error.isEmpty()) {
            localDataBaseBusiness.deleteAll()
            localDataBaseBusinessDetail.deleteAllBusinessDetails()
            remoteBusiness.businesses.map {
                localDataBaseBusiness.saveBusiness(it)
            }
        }
    }
}
