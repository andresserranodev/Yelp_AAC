package com.puzzlebench.yelp_aac.repository

import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessesByLocale
import com.puzzlebench.yelp_aac.repository.model.BusinessState

class BusinessRepositoryImpl constructor(
    private val remoteFetchBusinessesByLocale: RemoteFetchBusinessesByLocale,
    private val localDataBaseBusiness: LocalDataBaseBusiness
) : BusinessRepository {
    override suspend fun getBusiness(locale: String): BusinessState {
        val localBusiness = localDataBaseBusiness.getBusinessByLocale(locale)
        return if (localBusiness.businesses.isEmpty()) {
            val remoteBusiness = remoteFetchBusinessesByLocale.fetchBusinessByLocation(locale)
            if (remoteBusiness.error.isEmpty()) {
                remoteBusiness.businesses.map {
                    localDataBaseBusiness.saveBusiness(it)
                }
            }
            // TODO Improve this using a Support sqlite query
            localDataBaseBusiness.getBusinessByLocale(locale)
        } else {
            localBusiness
        }
    }
}
