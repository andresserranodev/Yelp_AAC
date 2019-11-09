package com.puzzlebench.yelp_aac.data.mapper

import com.puzzlebench.yelp_aac.data.local.room.entity.CategoriesEntity
import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessDetailResponse
import com.puzzlebench.yelp_aac.data.remote.retrofit.CategoriesResponse
import com.puzzlebench.yelp_aac.presentation.model.BusinessDetails

class BusinessDetailMapper {

    fun transformServiceToRepository(service: BusinessDetailResponse) =
        BusinessDetails(
            service.businessId,
            getCategories(service.categories),
            service.photos
        )

    fun transformRepositoryToCategoriesEntity(businessId: String, title: String) =
        CategoriesEntity(0, businessId, title)

    private fun getCategories(categoriesResponse: List<CategoriesResponse>): List<String> =
        categoriesResponse.map { it.title }

    fun transformEntityToRepository(categoriesEntity: List<CategoriesEntity>) =
        BusinessDetails(
            categoriesEntity.first().businessId,
            categoriesEntity.map { it.title },
            listOf()
        )


}