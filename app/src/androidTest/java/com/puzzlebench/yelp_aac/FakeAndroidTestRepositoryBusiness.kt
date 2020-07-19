package com.puzzlebench.yelp_aac

import com.puzzlebench.yelp_aac.repository.BusinessRepository
import com.puzzlebench.yelp_aac.repository.model.BusinessState
import com.puzzlebench.yelp_aac.presentation.model.Business

class FakeAndroidTestRepositoryBusiness : BusinessRepository {
    override suspend fun getBusiness(): BusinessState {
        return BusinessState(getDummyListBusiness())
    }

    private fun getDummyListBusiness(): List<Business> = (1..20).map {
        getDummyBusiness(it.toString())
    }

    private fun getDummyBusiness(seed: String) = Business(
        "${"ONE"}$seed",
        "${"name"}$seed",
        "${"DummyBusinessFactory.IMAGE_URL"}$seed",
        "${"DummyBusinessFactory.DISPLAY_PHONE"}$seed",
        "${"DummyBusinessFactory.DISPLAY_ADDRESS"}$seed",
        true,
        4.5F,
        "${"DummyBusinessFactory.PRICE"}$seed"
    )
}