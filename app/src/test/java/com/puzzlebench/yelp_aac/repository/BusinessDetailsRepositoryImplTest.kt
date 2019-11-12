package com.puzzlebench.yelp_aac.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.yelp_aac.DummyBusinessDetailsFactory.getBusinessDetailsNoError
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusinessDetail
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessDetailsById
import com.puzzlebench.yelp_aac.repository.model.BusinessDetailsState
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BusinessDetailsRepositoryImplTest {

    private val businessId = "1"
    private lateinit var businessDetailsRepository: BusinessDetailsRepository
    private val serviceResponse = getBusinessDetailsNoError()
    private val remoteFetchBusinessDetailsById = mock<RemoteFetchBusinessDetailsById> {
        onBlocking { fetchBusinessDetailsById(businessId) } doReturn serviceResponse
    }


    @Test
    fun `test empty local data`() {
        val businessLocalData = mock<LocalDataBaseBusinessDetail> {
            onBlocking {
                getBusinessDetailsByBusinessId(businessId)
            } doReturn BusinessDetailsState(
                null
            )
        }
        businessDetailsRepository =
            BusinessDetailsRepositoryImpl(remoteFetchBusinessDetailsById, businessLocalData)

        runBlocking {
            businessDetailsRepository.getBusinessDetailsById(businessId)
            verify(businessLocalData).getBusinessDetailsByBusinessId(businessId)
            verify(remoteFetchBusinessDetailsById).fetchBusinessDetailsById(businessId)
            serviceResponse.businessDetails?.let {
                verify(businessLocalData).insertBusinessDetails(it)
            }
        }
    }

    @Test
    fun `test no empty local data`() {
        val businessLocalData = mock<LocalDataBaseBusinessDetail> {
            onBlocking {
                getBusinessDetailsByBusinessId(businessId)
            } doReturn getBusinessDetailsNoError()
        }
        businessDetailsRepository =
            BusinessDetailsRepositoryImpl(remoteFetchBusinessDetailsById, businessLocalData)

        runBlocking {
            businessDetailsRepository.getBusinessDetailsById(businessId)
            verify(businessLocalData).getBusinessDetailsByBusinessId(businessId)
        }
    }
}
