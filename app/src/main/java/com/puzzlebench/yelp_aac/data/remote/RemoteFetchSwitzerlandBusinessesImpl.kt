package com.puzzlebench.yelp_aac.data.remote

import com.puzzlebench.yelp_aac.repository.model.BussinesState
import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.remote.retrofit.YelpApiV3
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RemoteFetchSwitzerlandBusinessesImpl constructor(
    private val api: YelpApiV3,
    private val mapper: BusinessMapper
) : RemoteFetchSwitzerlandBusinesses {
    override suspend fun fetchSwitzerlandBusiness(): BussinesState = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = api.getSwitzerlandBusiness().let { baseResponse ->
                baseResponse.businesses.map {
                    mapper.transformServiceToRepository(it)
                }
            }
            BussinesState(response)
        } catch (ex: Exception) {
            BussinesState(
                listOf(),
                ex.message.toString()
            )
        }
    }
}
