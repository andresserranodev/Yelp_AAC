package com.puzzlebench.yelp_aac.repository

import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusinessDetail
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessesByLocal

const val LOCAL_DEFAULT = "Switzerland"

class UpdateRepositoryImpl constructor(
    private val remoteFetchBusinessesByLocal: RemoteFetchBusinessesByLocal,
    private val localDataBaseBusiness: LocalDataBaseBusiness,
    private val localDataBaseBusinessDetail: LocalDataBaseBusinessDetail
) : UpdateRepository {
    override suspend fun updateBusiness() {
        val remoteBusiness =
            remoteFetchBusinessesByLocal.fetchBusinessByLocation(LOCAL_DEFAULT)
        if (remoteBusiness.error.isEmpty()) {
            localDataBaseBusiness.deleteAll()
            localDataBaseBusinessDetail.deleteAllBusinessDetails()
            remoteBusiness.businesses.map {
                localDataBaseBusiness.saveBusiness(it)
            }
        }
    }
}
