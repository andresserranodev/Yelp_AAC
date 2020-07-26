package com.puzzlebench.yelp_aac.data.remote

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getDummyYepResponse
import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.remote.retrofit.YelpApiV3
import com.puzzlebench.yelp_aac.data.remote.retrofit.transformServiceToRepository
import com.puzzlebench.yelp_aac.repository.LOCALE_DEFAULT
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
class RemoteFetchBusinessesByLocaleImplTest {
    private lateinit var remoteFetchBusinessesByLocale: RemoteFetchBusinessesByLocale

    private val serviceResponse = getDummyYepResponse()
    private val service = mock<YelpApiV3> {
        onBlocking { getBusinessByLocation(LOCALE_DEFAULT) } doReturn serviceResponse
    }
    private var businessMapper = mock<BusinessMapper>()

    @Before
    fun setUp() {
        remoteFetchBusinessesByLocale =
            RemoteFetchBusinessesByLocaleImpl(service, businessMapper)
    }

    @Test
    fun getSwitzerlandBusiness() {
        runBlocking {
            remoteFetchBusinessesByLocale.fetchBusinessByLocation(LOCALE_DEFAULT)
            verify(service).getBusinessByLocation(LOCALE_DEFAULT)
        }
    }
}
