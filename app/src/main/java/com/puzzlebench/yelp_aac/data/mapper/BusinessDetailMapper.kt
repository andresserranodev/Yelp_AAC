package com.puzzlebench.yelp_aac.data.mapper

import com.puzzlebench.yelp_aac.data.local.room.entity.CategoriesEntity
import com.puzzlebench.yelp_aac.data.local.room.entity.PhotoEntity
import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessDetailResponse
import com.puzzlebench.yelp_aac.data.remote.retrofit.CategoriesResponse
import com.puzzlebench.yelp_aac.presentation.details.BusinessDetails

class BusinessDetailMapper {

    fun transformServiceToRepository(service: BusinessDetailResponse) =
        BusinessDetails(
            service.businessId,
            getCategories(service.categories),
            service.photos
        )

    fun transformRepositoryToCategoriesEntity(businessId: String, title: String) =
        CategoriesEntity(businessId, title)

    fun transformPhotoRepositoryToEntity(businessId: String, url: String) =
        PhotoEntity(businessId, url)

    private fun getCategories(categoriesResponse: List<CategoriesResponse>): List<String> =
        categoriesResponse.map { it.title }

    fun transformEntityToRepository(
        categoriesEntity: List<CategoriesEntity>,
        photoEntity: List<PhotoEntity>
    ) =
        BusinessDetails(
            categoriesEntity.first().businessId,
            categoriesEntity.map { it.title },
            photoEntity.map { it.photoUrl }
        )


}
