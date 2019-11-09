package com.puzzlebench.yelp_aac

import com.puzzlebench.yelp_aac.data.local.room.entity.BusinessEntity
import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessResponse
import com.puzzlebench.yelp_aac.data.remote.retrofit.YelpResponse
import com.puzzlebench.yelp_aac.presentation.model.Business
import com.puzzlebench.yelp_aac.repository.model.BusinessState

object DummyBusinessFactory {
    const val BUSINESS_ID = "BUSINESS_ID"
    const val BASE_NAME = "Dummy Name "
    const val IMAGE_URL = "http:www.dummy.com"
    const val DISPLAY_PHONE = "(121)-3123132"
    const val PRICE = "$$$"

    fun getDummyYepResponse() = YelpResponse(getDummyListBusinessResponse())

    fun getDummyBusinessResponse(seed: String) = BusinessResponse(
        "$BUSINESS_ID$seed",
        "$BASE_NAME$seed",
        "$IMAGE_URL$seed",
        "$DISPLAY_PHONE$seed",
        "$PRICE$seed"
    )

    fun getDummyBusinessResponseNullPrice(seed: String) = BusinessResponse(
        "$BUSINESS_ID$seed",
        "$BASE_NAME$seed",
        "$IMAGE_URL$seed",
        "$DISPLAY_PHONE$seed",
        null
    )

    fun getDummyBusinessEntity(seed: String) =
        BusinessEntity(
            "$BUSINESS_ID$seed",
            "$BASE_NAME$seed",
            "$IMAGE_URL$seed",
            "$DISPLAY_PHONE$seed",
            "$PRICE$seed"
        )


    fun getDummyListBusinessEntity(): List<BusinessEntity> = (1..20).map {
        getDummyBusinessEntity(it.toString())
    }

    fun getDummyListBusiness(): List<Business> = (1..20).map {
        getDummyBusiness(it.toString())
    }

    fun getDummyBusiness(seed: String) = Business(
        "$BUSINESS_ID$seed",
        "$BASE_NAME$seed",
        "$IMAGE_URL$seed",
        "$DISPLAY_PHONE$seed",
        "$PRICE$seed"
    )

    fun getBussinesStateError() = BusinessState(listOf(), "Error")

    fun getBussinesStateEmpty() = BusinessState(listOf())


    fun getBussinesStateNoError() = BusinessState(getDummyListBusiness())

    private fun getDummyListBusinessResponse(): List<BusinessResponse> = (1..20).map {
        getDummyBusinessResponse(it.toString())
    }

}