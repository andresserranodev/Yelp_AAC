package com.puzzlebench.yelp_aac.data.remote

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getDummyYepResponse
import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.remote.retrofit.YelpApiV3
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RemoteFetchSwitzerlandBusinessesImplTest {
    private lateinit var remoteFetchSwitzerlandBusinesses: RemoteFetchSwitzerlandBusinesses

    private val serviceResponse = getDummyYepResponse()
    private val service = mock<YelpApiV3> {
        onBlocking { getSwitzerlandBusiness() } doReturn serviceResponse
    }
    private var businessMapper = mock<BusinessMapper>()

    @Before
    fun setUp() {
        remoteFetchSwitzerlandBusinesses =
            RemoteFetchSwitzerlandBusinessesImpl(service, businessMapper)
    }

    @Test
    fun getSwitzerlandBusiness() {
        runBlocking {
            remoteFetchSwitzerlandBusinesses.fetchSwitzerlandBusiness()
            verify(service).getSwitzerlandBusiness()
            serviceResponse.businesses.forEach {
                verify(businessMapper).transformServiceToRepository(it)
            }
        }
    }
}
