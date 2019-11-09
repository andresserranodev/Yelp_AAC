package com.puzzlebench.yelp_aac.data.mapper

import com.puzzlebench.yelp_aac.DummyBusinessFactory.getDummyBusiness
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getDummyBusinessEntity
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getDummyBusinessResponse
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getDummyBusinessResponseNullPrice
import com.puzzlebench.yelp_aac.data.local.room.entity.BusinessEntity
import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessResponse
import com.puzzlebench.yelp_aac.presentation.model.Business
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

    @Test
    fun transformEntityToRepository() {
        val entity = getDummyBusinessEntity("1")
        val result = mapper.transformEntityToRepository(entity)
        assertDataEquality(entity, result)
    }

    @Test
    fun transformRepositoryToEntity() {
        val repository = getDummyBusiness("1")
        val result = mapper.transformRepositoryToEntity(repository)
        assertDataEquality(result, repository)
    }

    private fun assertDataEquality(response: BusinessResponse, repository: Business) {
        assertEquals(repository.businessId, response.businessId)
        assertEquals(repository.name, response.name)
        assertEquals(repository.imageUrl, response.imageUrl)
        assertEquals(repository.displayPhone, response.displayPhone)
    }

    private fun assertDataEquality(entity: BusinessEntity, repository: Business) {
        assertEquals(repository.businessId, entity.businessId)
        assertEquals(repository.name, entity.name)
        assertEquals(repository.imageUrl, entity.imageUrl)
        assertEquals(repository.displayPhone, entity.displayPhone)
    }

}