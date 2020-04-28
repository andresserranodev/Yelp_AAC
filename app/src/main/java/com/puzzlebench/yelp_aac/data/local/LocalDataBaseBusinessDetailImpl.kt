package com.puzzlebench.yelp_aac.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.puzzlebench.yelp_aac.data.local.room.dao.BusinessDao
import com.puzzlebench.yelp_aac.data.local.room.dao.CategoriesDao
import com.puzzlebench.yelp_aac.data.local.room.dao.PhotoDao
import com.puzzlebench.yelp_aac.data.mapper.BusinessDetailMapper
import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.mapper.UNDEFINED_ERROR_LOCAL_DATA_BASE_BUSINESS_DETAILS
import com.puzzlebench.yelp_aac.presentation.model.Business
import com.puzzlebench.yelp_aac.presentation.model.BusinessDetails
import com.puzzlebench.yelp_aac.repository.model.BusinessDetailsState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataBaseBusinessDetailImpl constructor(
    private val businessDao: BusinessDao,
    private val categoriesDao: CategoriesDao,
    private val photoDao: PhotoDao,
    private val mapper: BusinessDetailMapper,
    private val businessMapper: BusinessMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalDataBaseBusinessDetail {

    override fun getBusinessById(businessId: String): LiveData<Business> {
        return Transformations.map(businessDao.getBusinessById(businessId)) {
            businessMapper.transformEntityToRepository(it)
        }
    }

    override suspend fun getBusinessDetailsByBusinessId(businessId: String): BusinessDetailsState =
        withContext(ioDispatcher) {
            return@withContext try {
                val resultCategory = categoriesDao.getCategoriesByBusinessId(businessId)
                val resultPhoto = photoDao.getPhotosByBusinessId(businessId)
                BusinessDetailsState(
                    mapper.transformEntityToRepository(
                        resultCategory,
                        resultPhoto
                    )
                )
            } catch (e: Exception) {
                BusinessDetailsState(
                    null,
                    e.message ?: UNDEFINED_ERROR_LOCAL_DATA_BASE_BUSINESS_DETAILS
                )
            }
        }

    override suspend fun insertBusinessDetails(business: BusinessDetails) {
        business.categories.map {
            categoriesDao.insertCategories(
                mapper.transformRepositoryToCategoriesEntity(
                    business.businessId,
                    it
                )
            )
        }

        business.photos.map {
            photoDao.insertPhoto(
                mapper.transformPhotoRepositoryToEntity(
                    business.businessId,
                    it
                )
            )
        }
    }

    override suspend fun deleteAllBusinessDetails() = withContext(ioDispatcher) {
        categoriesDao.deleteAllCategories()
        photoDao.deleteAllPhotos()
    }
}
