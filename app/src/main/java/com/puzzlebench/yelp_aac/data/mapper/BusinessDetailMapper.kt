package com.puzzlebench.yelp_aac.data.mapper

import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessDetailResponse
import com.puzzlebench.yelp_aac.data.remote.retrofit.CategoriesResponse
import com.puzzlebench.yelp_aac.presentation.BusinessDetails

class BusinessDetailMapper {
    fun transformServiceToRepository(service: BusinessDetailResponse) =
        BusinessDetails(
            service.businessId,
            getCategories(service.categories),
            service.photos
        )

    private fun getCategories(categoriesResponse: List<CategoriesResponse>): List<String> =
        categoriesResponse.map { it.title }
}