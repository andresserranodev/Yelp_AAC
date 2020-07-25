package com.puzzlebench.yelp_aac.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getBussinesStateEmpty
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getBussinesStateError
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getBussinesStateNoError
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessesByLocale
import com.puzzlebench.yelp_aac.repository.model.NO_ERROR
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class BusinessRepositoryImplTest {

    private lateinit var businessRepositoryImpl: BusinessRepository

    @Test
    fun `test empty local data`() {
        val serviceResponse = getBussinesStateNoError()
        val fetchSwitzerlandBusinesses = mock<RemoteFetchBusinessesByLocale> {
            onBlocking { fetchBusinessByLocation(LOCALE_DEFAULT) } doReturn serviceResponse
        }

        val businessLocalData = mock<LocalDataBaseBusiness> {
            onBlocking { getBusiness() } doReturn getBussinesStateEmpty()
        }
        businessRepositoryImpl =
            BusinessRepositoryImpl(fetchSwitzerlandBusinesses, businessLocalData)
        runBlocking {
            businessRepositoryImpl.getBusiness(LOCALE_DEFAULT)
            verify(businessLocalData).getBusiness()
            verify(fetchSwitzerlandBusinesses).fetchBusinessByLocation(LOCALE_DEFAULT)
            serviceResponse.businesses.forEach {
                verify(businessLocalData).saveBusiness(it)
            }
        }
    }

    @Test
    fun `test get local data`() {
        val fetchSwitzerlandBusinesses = mock<RemoteFetchBusinessesByLocale>()
        val businessLocalData = mock<LocalDataBaseBusiness> {
            onBlocking { getBusiness() } doReturn getBussinesStateNoError()
        }
        businessRepositoryImpl =
            BusinessRepositoryImpl(fetchSwitzerlandBusinesses, businessLocalData)
        runBlocking {
            businessRepositoryImpl.getBusiness(LOCALE_DEFAULT)
            verify(businessLocalData).getBusiness()
            assertEquals(businessRepositoryImpl.getBusiness(LOCALE_DEFAULT).error, NO_ERROR)
        }
    }

    @Test
    fun `test error getting local data`() {
        val serviceResponse = getBussinesStateError()
        val fetchSwitzerlandBusinesses = mock<RemoteFetchBusinessesByLocale> {
            onBlocking { fetchBusinessByLocation(LOCALE_DEFAULT) } doReturn serviceResponse
        }

        val businessLocalData = mock<LocalDataBaseBusiness> {
            onBlocking { getBusiness() } doReturn getBussinesStateError()
        }
        businessRepositoryImpl =
            BusinessRepositoryImpl(fetchSwitzerlandBusinesses, businessLocalData)
        runBlocking {
            businessRepositoryImpl.getBusiness(LOCALE_DEFAULT)
            verify(businessLocalData).getBusiness()
            verify(fetchSwitzerlandBusinesses).fetchBusinessByLocation(LOCALE_DEFAULT)
            assertEquals(businessRepositoryImpl.getBusiness(LOCALE_DEFAULT).error, "Error")
        }
    }
}
