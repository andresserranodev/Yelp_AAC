package com.puzzlebench.yelp_aac

import com.puzzlebench.yelp_aac.repository.BusinessRepository
import com.puzzlebench.yelp_aac.repository.model.BusinessState
import com.puzzlebench.yelp_aac.presentation.model.Business

const val NAME_BUSINES = "name"

class FakeAndroidTestRepositoryBusiness : BusinessRepository {
    override suspend fun getBusiness(locale: String): BusinessState {
        return BusinessState(getDummyListBusiness())
    }

    private fun getDummyListBusiness(): List<Business> = (1..20).map {
        getDummyBusiness(it.toString())
    }

    private fun getDummyBusiness(seed: String) = Business(
        "${"ONE"}$seed",
        "${NAME_BUSINES}$seed",
        "${"DummyBusinessFactory.IMAGE_URL"}$seed",
        "${"DummyBusinessFactory.DISPLAY_PHONE"}$seed",
        "${"DummyBusinessFactory.DISPLAY_ADDRESS"}$seed",
        true,
        4.5F,
        "${"DummyBusinessFactory.PRICE"}$seed"
    )
}