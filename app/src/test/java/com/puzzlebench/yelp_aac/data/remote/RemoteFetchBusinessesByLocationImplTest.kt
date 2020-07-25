package com.puzzlebench.yelp_aac.data.remote

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getDummyYepResponse
import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.remote.retrofit.YelpApiV3
import com.puzzlebench.yelp_aac.repository.DEFAULT_LOCATION
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
class RemoteFetchBusinessesByLocationImplTest {
    private lateinit var remoteFetchBusinessesByLocation: RemoteFetchBusinessesByLocation

    private val serviceResponse = getDummyYepResponse()
    private val service = mock<YelpApiV3> {
        onBlocking { getBusinessByLocation(DEFAULT_LOCATION) } doReturn serviceResponse
    }
    private var businessMapper = mock<BusinessMapper>()

    @Before
    fun setUp() {
        remoteFetchBusinessesByLocation =
            RemoteFetchBusinessesByLocationImpl(service, businessMapper)
    }

    @Test
    fun getSwitzerlandBusiness() {
        runBlocking {
            remoteFetchBusinessesByLocation.fetchBusinessByLocation(DEFAULT_LOCATION)
            verify(service).getBusinessByLocation(DEFAULT_LOCATION)
            serviceResponse.businesses.forEach {
                verify(businessMapper).transformServiceToRepository(it)
            }
        }
    }
}
