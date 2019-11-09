package com.puzzlebench.yelp_aac.data.mapper

import com.puzzlebench.yelp_aac.data.local.room.entity.BusinessEntity
import com.puzzlebench.yelp_aac.presentation.model.Business
import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessResponse

class BusinessMapper : BaseMapperData<BusinessResponse, Business, BusinessEntity> {

    override fun transformRepositoryToEntity(repository: Business) =
        BusinessEntity(
            repository.businessId,
            repository.name,
            repository.imageUrl,
            repository.displayPhone,
            repository.price
        )

    override fun transformEntityToRepository(entity: BusinessEntity) =
        Business(
            entity.businessId,
            entity.name,
            entity.imageUrl,
            entity.displayPhone,
            entity.price
        )

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
