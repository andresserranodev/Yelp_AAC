package com.puzzlebench.yelp_aac.data.remote

import com.puzzlebench.yelp_aac.presentation.BussinesState
import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.remote.retofit.YelpApiV3
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RemoteFetchSwitzerlandBusinessesIml constructor(
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