package com.puzzlebench.yelp_aac.data.local

import com.puzzlebench.yelp_aac.data.local.room.dao.BusinessDao
import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.mapper.UNDEFINED_ERROR_LOCAL_DATA_BASE_BUSINESS
import com.puzzlebench.yelp_aac.presentation.model.Business
import com.puzzlebench.yelp_aac.repository.model.BusinessState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataBaseBusinessImpl internal constructor(
    private val businessDao: BusinessDao,
    private val mapper: BusinessMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalDataBaseBusiness {

    override suspend fun getBusiness(): BusinessState = withContext(ioDispatcher) {
        return@withContext try {
            BusinessState(businessDao.getBusiness().map {
                mapper.transformEntityToRepository(
                    it
                )
            })
        } catch (e: Exception) {
            BusinessState(listOf(), e.message ?: UNDEFINED_ERROR_LOCAL_DATA_BASE_BUSINESS)
        }

    }

    override suspend fun saveBusiness(business: Business) {
        businessDao.insertBusiness(mapper.transformRepositoryToEntity(business))
    }

    override suspend fun deleteAll() {
        businessDao.deleteAllBusiness()
    }
}
