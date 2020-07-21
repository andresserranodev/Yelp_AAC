package com.puzzlebench.yelp_aac.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getBussinesStateEmpty
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getBussinesStateError
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getBussinesStateNoError
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchSwitzerlandBusinesses
import com.puzzlebench.yelp_aac.repository.model.NO_ERROR
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class BusinessRepositoryImplTest {

    private lateinit var businessRepositoryImpl: BusinessRepository

    @Test
    fun `test empty local data`() {
        val serviceResponse = getBussinesStateNoError()
        val fetchSwitzerlandBusinesses = mock<RemoteFetchSwitzerlandBusinesses> {
            onBlocking { fetchSwitzerlandBusiness() } doReturn serviceResponse
        }

        val businessLocalData = mock<LocalDataBaseBusiness> {
            onBlocking { getBusiness() } doReturn getBussinesStateEmpty()
        }
        businessRepositoryImpl =
            BusinessRepositoryImpl(fetchSwitzerlandBusinesses, businessLocalData)
        runBlocking {
            businessRepositoryImpl.getBusiness()
            verify(businessLocalData).getBusiness()
            verify(fetchSwitzerlandBusinesses).fetchSwitzerlandBusiness()
            serviceResponse.businesses.forEach {
                verify(businessLocalData).saveBusiness(it)
            }
        }
    }

    @Test
    fun `test get local data`() {
        val fetchSwitzerlandBusinesses = mock<RemoteFetchSwitzerlandBusinesses>()
        val businessLocalData = mock<LocalDataBaseBusiness> {
            onBlocking { getBusiness() } doReturn getBussinesStateNoError()
        }
        businessRepositoryImpl =
            BusinessRepositoryImpl(fetchSwitzerlandBusinesses, businessLocalData)
        runBlocking {
            businessRepositoryImpl.getBusiness()
            verify(businessLocalData).getBusiness()
            assertEquals(businessRepositoryImpl.getBusiness().error, NO_ERROR)
        }
    }

    @Test
    fun `test error getting local data`() {
        val serviceResponse = getBussinesStateError()
        val fetchSwitzerlandBusinesses = mock<RemoteFetchSwitzerlandBusinesses> {
            onBlocking { fetchSwitzerlandBusiness() } doReturn serviceResponse
        }

        val businessLocalData = mock<LocalDataBaseBusiness> {
            onBlocking { getBusiness() } doReturn getBussinesStateError()
        }
        businessRepositoryImpl =
            BusinessRepositoryImpl(fetchSwitzerlandBusinesses, businessLocalData)
        runBlocking {
            businessRepositoryImpl.getBusiness()
            verify(businessLocalData).getBusiness()
            verify(fetchSwitzerlandBusinesses).fetchSwitzerlandBusiness()
            assertEquals(businessRepositoryImpl.getBusiness().error, "Error")
        }
    }
}
