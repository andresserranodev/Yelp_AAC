package com.puzzlebench.yelp_aac.data.remote

import com.puzzlebench.yelp_aac.data.mapper.BusinessDetailMapper
import com.puzzlebench.yelp_aac.data.remote.retrofit.YelpApiV3
import com.puzzlebench.yelp_aac.repository.model.BusinessDetailsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteFetchBusinessDetailsByIdImpl constructor(
    private val api: YelpApiV3,
    private val mapper: BusinessDetailMapper
) : RemoteFetchBusinessDetailsById {
    override suspend fun fetchBusinessDetailsById(businessId: String): BusinessDetailsState =
        withContext(
            Dispatchers.IO
        ) {
            return@withContext try {
                val response = api.getBusinessDetailsById(businessId)
                val businessDetails = mapper.transformServiceToRepository(response)
                BusinessDetailsState(
                    businessDetails
                )
            } catch (ex: Exception) {
                BusinessDetailsState(
                    null,
                    ex.message.toString()
                )
            }
        }
}
