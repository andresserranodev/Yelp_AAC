package com.puzzlebench.yelp_aac.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.yelp_aac.DummyBusinessDetailsFactory.getBusinessDetailsDataError
import com.puzzlebench.yelp_aac.DummyBusinessDetailsFactory.getBusinessDetailsNoError
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class DetailsBusinessViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule: CoroutinesTestRule = CoroutinesTestRule()

    private val businessId = "1"

    private lateinit var detailsBusinessViewModel: DetailsBusinessViewModel
    private val businessDetailsRepository = mock<BusinessDetailsRepository> {
        onBlocking { getBusinessDetailsById(businessId) } doReturn getBusinessDetailsNoError()
    }

    @Before
    fun setUp() {
        detailsBusinessViewModel = DetailsBusinessViewModel(businessDetailsRepository, businessId)
    }

    @Test
    fun getBusinessDetails() {

        detailsBusinessViewModel.getBusinessDetails()
        runBlocking {
            verify(businessDetailsRepository).getBusinessDetailsById(businessId)
        }
    }

    @Test
    fun getBusinessDetailsError() {
        val businessDetailsErrorRepository = mock<BusinessDetailsRepository> {
            onBlocking { getBusinessDetailsById(businessId) } doReturn getBusinessDetailsDataError()
        }
        detailsBusinessViewModel =
            DetailsBusinessViewModel(businessDetailsErrorRepository, businessId)

        detailsBusinessViewModel.getBusinessDetails()
        runBlocking {
            verify(businessDetailsErrorRepository).getBusinessDetailsById(businessId)
        }
    }
}
