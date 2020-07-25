package com.puzzlebench.yelp_aac.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getBussinesStateEmpty
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getBussinesStateError
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getBussinesStateNoError
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessesByLocal
import com.puzzlebench.yelp_aac.repository.model.NO_ERROR
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class BusinessRepositoryImplTest {

    private lateinit var businessRepositoryImpl: BusinessRepository

    @Test
    fun `test empty local data`() {
        val serviceResponse = getBussinesStateNoError()
        val fetchSwitzerlandBusinesses = mock<RemoteFetchBusinessesByLocal> {
            onBlocking { fetchBusinessByLocation(LOCAL_DEFAULT) } doReturn serviceResponse
        }

        val businessLocalData = mock<LocalDataBaseBusiness> {
            onBlocking { getBusiness() } doReturn getBussinesStateEmpty()
        }
        businessRepositoryImpl =
            BusinessRepositoryImpl(fetchSwitzerlandBusinesses, businessLocalData)
        runBlocking {
            businessRepositoryImpl.getBusiness(LOCAL_DEFAULT)
            verify(businessLocalData).getBusiness()
            verify(fetchSwitzerlandBusinesses).fetchBusinessByLocation(LOCAL_DEFAULT)
            serviceResponse.businesses.forEach {
                verify(businessLocalData).saveBusiness(it)
            }
        }
    }

    @Test
    fun `test get local data`() {
        val fetchSwitzerlandBusinesses = mock<RemoteFetchBusinessesByLocal>()
        val businessLocalData = mock<LocalDataBaseBusiness> {
            onBlocking { getBusiness() } doReturn getBussinesStateNoError()
        }
        businessRepositoryImpl =
            BusinessRepositoryImpl(fetchSwitzerlandBusinesses, businessLocalData)
        runBlocking {
            businessRepositoryImpl.getBusiness(LOCAL_DEFAULT)
            verify(businessLocalData).getBusiness()
            assertEquals(businessRepositoryImpl.getBusiness(LOCAL_DEFAULT).error, NO_ERROR)
        }
    }

    @Test
    fun `test error getting local data`() {
        val serviceResponse = getBussinesStateError()
        val fetchSwitzerlandBusinesses = mock<RemoteFetchBusinessesByLocal> {
            onBlocking { fetchBusinessByLocation(LOCAL_DEFAULT) } doReturn serviceResponse
        }

        val businessLocalData = mock<LocalDataBaseBusiness> {
            onBlocking { getBusiness() } doReturn getBussinesStateError()
        }
        businessRepositoryImpl =
            BusinessRepositoryImpl(fetchSwitzerlandBusinesses, businessLocalData)
        runBlocking {
            businessRepositoryImpl.getBusiness(LOCAL_DEFAULT)
            verify(businessLocalData).getBusiness()
            verify(fetchSwitzerlandBusinesses).fetchBusinessByLocation(LOCAL_DEFAULT)
            assertEquals(businessRepositoryImpl.getBusiness(LOCAL_DEFAULT).error, "Error")
        }
    }
}
