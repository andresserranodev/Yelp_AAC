package com.puzzlebench.yelp_aac.data.mapper

import com.puzzlebench.yelp_aac.presentation.Business
import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessResponse

class BusinessMapper : BaseMapperData<BusinessResponse, Business> {

    override fun transformServiceToRepository(service: BusinessResponse) =
        Business(
            service.businessId,
            service.name,
            service.imageUrl,
            service.displayPhone,
            getPrice(service.price)
        )

    private fun getPrice(price: String?): String {
        return price ?: PRICE_EMPTY_VALUE
    }
}