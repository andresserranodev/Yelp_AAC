package com.puzzlebench.yelp_aac.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.yelp_aac.DummyBusinessFactory
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusinessDetail
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessesByLocale
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UpdateRepositoryImplTest {

    private lateinit var updateRepository: UpdateRepository
    private val serviceResponse = DummyBusinessFactory.getBussinesStateNoError()

    private val mockFetchSwitzerlandBusinesses = mock<RemoteFetchBusinessesByLocale> {
        onBlocking { fetchBusinessByLocation(LOCALE_DEFAULT) } doReturn serviceResponse
    }
    private val mockBusinessLocalData = mock<LocalDataBaseBusiness>()
    private val localDataBaseBusinessDetail = mock<LocalDataBaseBusinessDetail>()

    @Before
    fun setUp() {
        updateRepository = UpdateRepositoryImpl(
            mockFetchSwitzerlandBusinesses,
            mockBusinessLocalData,
            localDataBaseBusinessDetail
        )
    }

    @Test
    fun updateBusiness() {
        runBlocking {
        updateRepository.updateBusiness()
            verify(mockFetchSwitzerlandBusinesses).fetchBusinessByLocation(LOCALE_DEFAULT)
            verify(mockBusinessLocalData).deleteAll()
            verify(localDataBaseBusinessDetail).deleteAllBusinessDetails()
            serviceResponse.businesses.forEach {
                verify(mockBusinessLocalData).saveBusiness(it)
            }
        }
    }
}
