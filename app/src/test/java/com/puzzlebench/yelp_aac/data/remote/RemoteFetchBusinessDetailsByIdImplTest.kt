package com.puzzlebench.yelp_aac.data.remote

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.yelp_aac.DummyBusinessDetailsFactory.getDummyBusinessDetailResponse
import com.puzzlebench.yelp_aac.data.mapper.BusinessDetailMapper
import com.puzzlebench.yelp_aac.data.remote.retrofit.YelpApiV3
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RemoteFetchBusinessDetailsByIdImplTest {

    private val businessId = "hduyashad-adads"
    private lateinit var remoteFetchBusinessDetailsById: RemoteFetchBusinessDetailsById
    private val serviceResponse = getDummyBusinessDetailResponse("1")
    private val service = mock<YelpApiV3> {
        onBlocking { getBusinessDetailsById(businessId) } doReturn serviceResponse
    }
    private val mapper = mock<BusinessDetailMapper>()
    @Before
    fun setUp() {
        remoteFetchBusinessDetailsById = RemoteFetchBusinessDetailsByIdImpl(service, mapper)
    }

    @Test
    fun getBusinessDetailsById() {
        runBlocking {
            remoteFetchBusinessDetailsById.fetchBusinessDetailsById(businessId)
            verify(service).getBusinessDetailsById(businessId)
            verify(mapper).transformServiceToRepository(serviceResponse)
        }
    }
}