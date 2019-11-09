package com.puzzlebench.yelp_aac.repository

import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusinessDetail
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessDetailsById
import com.puzzlebench.yelp_aac.repository.model.BusinessDetailsState

class BusinessDetailsRepositoryImpl constructor(
    private val remoteFetchBusinessDetailsById: RemoteFetchBusinessDetailsById,
    private val localDataBaseBusinessDetail: LocalDataBaseBusinessDetail
) :
    BusinessDetailsRepository {
    override suspend fun getBusinessDetailsById(businessId: String): BusinessDetailsState {
        val localData = localDataBaseBusinessDetail.getBusinessDetailsByBusinessId(businessId)
        if (localData.businessDetails == null) {
            val remoteData = remoteFetchBusinessDetailsById.fetchBusinessDetailsById(businessId)
            if (remoteData.error.isEmpty()) {
                remoteData.businessDetails?.let {
                    localDataBaseBusinessDetail.insertBusinessDetails(it)
                }
            }
            return remoteData
        }
        return localData
    }
}