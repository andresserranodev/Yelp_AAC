package com.puzzlebench.yelp_aac.data.mapper

import com.puzzlebench.yelp_aac.DummyBusinessDetailsFactory.getDummyBusinessDetailResponse
import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessDetailResponse
import com.puzzlebench.yelp_aac.presentation.BusinessDetails
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BusinessDetailMapperTest {

    private lateinit var mapper: BusinessDetailMapper

    @Before
    fun setUp() {
        mapper = BusinessDetailMapper()
    }

    @Test
    fun transformServiceToRepository() {
        val response = getDummyBusinessDetailResponse("1")
        val result = mapper.transformServiceToRepository(response)
        assertDataEquality(response, result)
    }

    private fun assertDataEquality(response: BusinessDetailResponse, repository: BusinessDetails) {
        assertEquals(repository.businessId, response.businessId)
        response.categories.forEachIndexed { index, responseTitle ->
            assertEquals(repository.categories[index], responseTitle.title)
        }
        response.photos.forEachIndexed { index, responseTitle ->
            assertEquals(repository.photos[index], responseTitle)
        }
    }
}