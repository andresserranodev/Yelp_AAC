package com.puzzlebench.yelp_aac

import com.puzzlebench.yelp_aac.data.remote.retofit.BusinessResponse
import com.puzzlebench.yelp_aac.data.remote.retofit.YelpResponse

object DummyBusinessFactory {
    const val BUSINESS_ID = "BUSINESS_ID"
    const val BASE_NAME = "Dummy Name "
    const val IMAGE_URL = "http:www.dummy.com"
    const val DISPLAY_PHONE = "(121)-3123132"
    const val PRICE = "$$$"

    fun getDummyYepResponse() = YelpResponse(getDummyListBusinessResponse())

    private fun getDummyListBusinessResponse(): List<BusinessResponse> = (1..20).map {
        getDummyBusinessResponse(it.toString())
    }

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

}