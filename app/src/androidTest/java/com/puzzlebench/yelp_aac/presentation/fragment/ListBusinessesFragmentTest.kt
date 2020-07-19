package com.puzzlebench.yelp_aac.presentation.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.puzzlebench.yelp_aac.FakeAndroidTestRepositoryBusiness
import com.puzzlebench.yelp_aac.R
import com.puzzlebench.yelp_aac.presentation.di.ServiceLocator
import com.puzzlebench.yelp_aac.repository.BusinessRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ListBusinessesFragmentTest {
    private lateinit var repository: BusinessRepository

    @Test
    fun activityFragmentLaunch() {
        repository = FakeAndroidTestRepositoryBusiness()
        ServiceLocator.businessRepository = repository
        launchFragmentInContainer<ListBusinessesFragment>(null, R.style.AppTheme)
    }
}