package com.puzzlebench.yelp_aac.data.remote

import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.remote.retrofit.YelpApiV3
import com.puzzlebench.yelp_aac.repository.model.BusinessState
import java.lang.Exception
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteFetchBusinessesByLocalImpl constructor(
    private val api: YelpApiV3,
    private val mapper: BusinessMapper
) : RemoteFetchBusinessesByLocal {
    override suspend fun fetchBusinessByLocation(local: String): BusinessState =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = api.getBusinessByLocation(local).let { baseResponse ->
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
