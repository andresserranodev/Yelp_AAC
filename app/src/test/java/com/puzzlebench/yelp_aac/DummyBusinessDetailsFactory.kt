package com.puzzlebench.yelp_aac

import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessDetailResponse
import com.puzzlebench.yelp_aac.data.remote.retrofit.CategoriesResponse

object DummyBusinessDetailsFactory {

    const val BUSINESS_ID = "BUSINESS_ID"
    const val CATEGORY_TITLE = "category title"
    const val URL_PHOTO = "www.photo.com"

    fun getDummyBusinessDetailResponse(seed: String) = BusinessDetailResponse(
        "${BUSINESS_ID}$seed",
        getDummyListCategoriesResponse(),
        getDummyListPhotoResponse()
    )

    private fun getDummyCategoriesResponse(seed: String) =
        CategoriesResponse("$CATEGORY_TITLE$seed")


    private fun getDummyListCategoriesResponse(): List<CategoriesResponse> = (1..20).map {
        getDummyCategoriesResponse(it.toString())
    }

    private fun getDummyListPhotoResponse(): List<String> = (1..20).map {
        "$URL_PHOTO$it"
    }

}