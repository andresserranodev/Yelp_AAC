package com.puzzlebench.yelp_aac.data.mapper

import com.puzzlebench.yelp_aac.data.local.room.entity.BusinessEntity
import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessResponse
import com.puzzlebench.yelp_aac.presentation.model.Business
import com.puzzlebench.yelp_aac.repository.LOCALE_DEFAULT

class BusinessMapper : BaseMapperData<BusinessResponse, Business, BusinessEntity> {

    override fun transformRepositoryToEntity(repository: Business) =
        BusinessEntity(
            repository.businessId,
            repository.name,
            repository.imageUrl,
            repository.displayPhone,
            repository.displayAddress,
            repository.isClosed,
            repository.rating,
            repository.price,
            repository.locale
        )

    override fun transformEntityToRepository(entity: BusinessEntity) =
        Business(
            entity.businessId,
            entity.name,
            entity.imageUrl,
            entity.displayPhone,
            entity.displayAddress,
            entity.isClosed,
            entity.rating,
            entity.price,
            entity.locale
        )

    override fun transformServiceToRepository(service: BusinessResponse) =
        Business(
            service.businessId,
            service.name,
            service.imageUrl,
            service.displayPhone,
            service.location.address1,
            service.isClosed,
            service.rating,
            getPrice(service.price),
            LOCALE_DEFAULT
        )

    private fun getPrice(price: String?): String {
        return price ?: PRICE_EMPTY_VALUE
    }
}
