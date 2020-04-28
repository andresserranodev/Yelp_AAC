package com.puzzlebench.yelp_aac

import com.puzzlebench.yelp_aac.data.local.room.entity.CategoriesEntity
import com.puzzlebench.yelp_aac.data.local.room.entity.PhotoEntity
import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessDetailResponse
import com.puzzlebench.yelp_aac.data.remote.retrofit.CategoriesResponse
import com.puzzlebench.yelp_aac.presentation.model.BusinessDetails
import com.puzzlebench.yelp_aac.repository.model.BusinessDetailsState

object DummyBusinessDetailsFactory {

    const val BUSINESS_ID = "BUSINESS_ID"
    const val CATEGORY_TITLE = "category title"
    const val URL_PHOTO = "www.photo.com"

    fun getDummyBusinessDetailResponse(seed: String) = BusinessDetailResponse(
        "${BUSINESS_ID}$seed",
        getDummyListCategoriesResponse(),
        getDummyListPhotoResponse()
    )

    fun getDummyListCategoriesEntity(): List<CategoriesEntity> = (1..20).map {
        getDummyCategoriesEntity(it.toString())
    }

    fun getDummyListPhotosEntity(): List<PhotoEntity> = (1..20).map {
        getDummyPhotosEntity(it.toString())
    }

    private fun getDummyCategoriesEntity(seed: String) =
        CategoriesEntity(
            "${BUSINESS_ID}$seed",
            "$CATEGORY_TITLE$seed"
        )

    private fun getDummyPhotosEntity(seed: String) =
        PhotoEntity(
            "${BUSINESS_ID}$seed",
            "$URL_PHOTO$seed"

        )

    private fun getDummyCategoriesResponse(seed: String) =
        CategoriesResponse("$CATEGORY_TITLE$seed")

    private fun getDummyListCategoriesResponse(): List<CategoriesResponse> = (1..20).map {
        getDummyCategoriesResponse(it.toString())
    }

    private fun getDummyListPhotoResponse(): List<String> = (1..20).map {
        "$URL_PHOTO$it"
    }

    fun getDummyBusinessDetails() = BusinessDetails("", listOf(), listOf())

    fun getBusinessDetailsNoError() = BusinessDetailsState(getDummyBusinessDetails())
}
