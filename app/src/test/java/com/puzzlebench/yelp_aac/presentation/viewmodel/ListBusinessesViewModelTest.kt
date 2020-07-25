package com.puzzlebench.yelp_aac.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getBussinesStateNoError
import com.puzzlebench.yelp_aac.repository.BusinessRepository
import com.puzzlebench.yelp_aac.repository.DEFAULT_LOCATION
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ListBusinessesViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule: CoroutinesTestRule = CoroutinesTestRule()
    private lateinit var listBusinessesViewModel: ListBusinessesViewModel

    private val businessRepository = mock<BusinessRepository> {
        onBlocking { getBusiness(DEFAULT_LOCATION) } doReturn getBussinesStateNoError()
    }

    @Before
    fun setUp() {
        listBusinessesViewModel = ListBusinessesViewModel(businessRepository)
    }

    @Test
    fun getBusiness() {
        listBusinessesViewModel.getBusiness()
        runBlocking {
            verify(businessRepository).getBusiness(DEFAULT_LOCATION)
        }
    }
}
