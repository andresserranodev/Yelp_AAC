package com.puzzlebench.yelp_aac.data.remote

import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.remote.retrofit.YelpApiV3
import com.puzzlebench.yelp_aac.repository.model.BusinessState
import java.lang.Exception
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteFetchSwitzerlandBusinessesImpl constructor(
    private val api: YelpApiV3,
    private val mapper: BusinessMapper
) : RemoteFetchSwitzerlandBusinesses {
    override suspend fun fetchSwitzerlandBusiness(): BusinessState = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = api.getBusinessByLocation("Switzerland").let { baseResponse ->
                baseResponse.businesses.map {
                    mapper.transformServiceToRepository(it)
                }
            }
            BusinessState(response)
        } catch (ex: Exception) {
            BusinessState(
                listOf(),
                ex.message.toString()
            )
        }
    }
}
