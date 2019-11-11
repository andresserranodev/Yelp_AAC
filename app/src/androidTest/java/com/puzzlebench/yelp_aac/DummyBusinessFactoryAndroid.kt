package com.puzzlebench.yelp_aac

import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessResponse
import com.puzzlebench.yelp_aac.data.remote.retrofit.Location
import com.puzzlebench.yelp_aac.presentation.model.Business
import com.puzzlebench.yelp_aac.presentation.model.BusinessDetails
import com.puzzlebench.yelp_aac.repository.model.BusinessDetailsState

object DummyBusinessFactoryAndroid {
    const val BUSINESS_ID = "BUSINESS_ID"
    const val BASE_NAME = "Dummy Name "
    const val IMAGE_URL = "http:www.dummy.com"
    const val DISPLAY_PHONE = "(121)-3123132"
    const val DISPLAY_ADDRESS = "dasdadsada"
    const val PRICE = "$$$"


    fun getDummyBusinessResponseIsClose(seed: String) = Business(
        "$BUSINESS_ID$seed",
        "$BASE_NAME$seed",
        "$IMAGE_URL$seed",
        "$DISPLAY_PHONE$seed",
        "$DISPLAY_ADDRESS$seed",
        true,
        4.5F,
        PRICE
    )

    fun getDummyBusinessResponseIsOpen(seed: String) = Business(
        "$BUSINESS_ID$seed",
        "$BASE_NAME$seed",
        "$IMAGE_URL$seed",
        "$DISPLAY_PHONE$seed",
        "$DISPLAY_ADDRESS$seed",
        false,
        4.5F,
        PRICE
    )


    fun getDyummyBusinessDetailsState() = BusinessDetailsState(
        getDummyBusinessDetails()
    )

    private fun getDummyBusinessDetails() = BusinessDetails(
        "1",
        listOf(),
        listOf()
    )
}