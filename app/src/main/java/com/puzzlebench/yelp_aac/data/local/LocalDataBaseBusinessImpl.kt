package com.puzzlebench.yelp_aac.data.local

import com.puzzlebench.yelp_aac.data.local.room.dao.BusinessDao
import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.presentation.model.Business
import com.puzzlebench.yelp_aac.presentation.model.BussinesState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataBaseBusinessImpl internal constructor(
    private val businessDao: BusinessDao,
    private val mapper: BusinessMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalDataBaseBusiness {

    override suspend fun getBusiness(): BussinesState = withContext(ioDispatcher) {
        return@withContext try {
            BussinesState(businessDao.getBusiness().map {
                mapper.transformEntityToRepository(
                    it
                )
            })
        } catch (e: Exception) {
            BussinesState(listOf(), e.message ?: "")
        }

    }

    override suspend fun saveBusiness(business: Business) {
        businessDao.insertBusiness(mapper.transformRepositoryToEntity(business))
    }

    override suspend fun deleteAll() {
        businessDao.deleteAllBusiness()
    }
}