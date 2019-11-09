package com.puzzlebench.yelp_aac.data.local

import com.puzzlebench.yelp_aac.data.local.room.dao.CategoriesDao
import com.puzzlebench.yelp_aac.data.mapper.BusinessDetailMapper
import com.puzzlebench.yelp_aac.presentation.model.BusinessDetails
import com.puzzlebench.yelp_aac.presentation.model.BusinessDetailsState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataBaseBusinessDetailImpl constructor(
    private val categoriesDao: CategoriesDao,
    private val mapper: BusinessDetailMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalDataBaseBusinessDetail {
    override suspend fun getBusinessDetailsByBusinessId(businessId: String): BusinessDetailsState =
        withContext(ioDispatcher) {
            return@withContext try {
                val resultCategory = categoriesDao.getCategoriesByBusinessId(businessId)
                BusinessDetailsState(
                    mapper.transformEntityToRepository(
                        resultCategory
                    ), ""
                )

            } catch (e: Exception) {
                BusinessDetailsState(null, e.message ?: "")
            }
        }

    override suspend fun insertBusinessDetails(business: BusinessDetails) {
        business.categories.map {
            categoriesDao.insertCategories(
                mapper.transformRepositoryToEntity(
                    business.businessId,
                    it
                )
            )
        }
    }

    override suspend fun deleteAllBusinessDetails() = withContext(ioDispatcher) {
        categoriesDao.deleteAllCategories()
    }

}