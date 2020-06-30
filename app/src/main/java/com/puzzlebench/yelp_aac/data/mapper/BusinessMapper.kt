package com.puzzlebench.yelp_aac.data.mapper

import com.puzzlebench.yelp_aac.data.local.room.entity.BusinessEntity
import com.puzzlebench.yelp_aac.presentation.list.Business
import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessResponse

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
            repository.price
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
            entity.price
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
            getPrice(service.price)
        )

    private fun getPrice(price: String?): String {
        return price ?: PRICE_EMPTY_VALUE
    }
}
