package com.puzzlebench.yelp_aac.data.remote

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getDummyYepResponse
import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.remote.retrofit.YelpApiV3
import com.puzzlebench.yelp_aac.repository.LOCAL_DEFAULT
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
class RemoteFetchBusinessesByLocalImplTest {
    private lateinit var remoteFetchBusinessesByLocal: RemoteFetchBusinessesByLocal

    private val serviceResponse = getDummyYepResponse()
    private val service = mock<YelpApiV3> {
        onBlocking { getBusinessByLocation(LOCAL_DEFAULT) } doReturn serviceResponse
    }
    private var businessMapper = mock<BusinessMapper>()

    @Before
    fun setUp() {
        remoteFetchBusinessesByLocal =
            RemoteFetchBusinessesByLocalImpl(service, businessMapper)
    }

    @Test
    fun getSwitzerlandBusiness() {
        runBlocking {
            remoteFetchBusinessesByLocal.fetchBusinessByLocation(LOCAL_DEFAULT)
            verify(service).getBusinessByLocation(LOCAL_DEFAULT)
            serviceResponse.businesses.forEach {
                verify(businessMapper).transformServiceToRepository(it)
            }
        }
    }
}
