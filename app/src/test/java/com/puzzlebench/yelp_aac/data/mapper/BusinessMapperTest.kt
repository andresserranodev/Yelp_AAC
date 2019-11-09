package com.puzzlebench.yelp_aac.data.mapper

import com.puzzlebench.yelp_aac.DummyBusinessFactory.getDummyBusinessResponse
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getDummyBusinessResponseNullPrice
import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessResponse
import com.puzzlebench.yelp_aac.presentation.Business
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class BusinessMapperTest {

    private lateinit var mapper: BusinessMapper

    @Before
    fun setUp() {
        mapper = BusinessMapper()
    }

    @Test
    fun transformServiceToRepository() {
        val response = getDummyBusinessResponse("1")
        val result = mapper.transformServiceToRepository(response)
        assertDataEquality(response, result)
        assertEquals(response.price, result.price)
    }

    @Test
    fun transformServiceToRepositoryNullPrice() {
        val response = getDummyBusinessResponseNullPrice("1")
        val result = mapper.transformServiceToRepository(response)
        assertDataEquality(response, result)
        assertEquals(PRICE_EMPTY_VALUE, result.price)
    }

    private fun assertDataEquality(response: BusinessResponse, repository: Business) {
        assertEquals(repository.businessId, response.businessId)
        assertEquals(repository.name, response.name)
        assertEquals(repository.imageUrl, response.imageUrl)
        assertEquals(repository.displayPhone, response.displayPhone)
    }

}