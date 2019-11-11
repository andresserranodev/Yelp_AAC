package com.puzzlebench.yelp_aac.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.puzzlebench.yelp_aac.FakeAndroidTestRepositoryBusinessClose
import com.puzzlebench.yelp_aac.FakeAndroidTestRepositoryBusinessOpen
import com.puzzlebench.yelp_aac.R
import com.puzzlebench.yelp_aac.ServiceLocator
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class DetailsBusinessFragmentTest {

    private lateinit var repository: BusinessDetailsRepository

    @Test
    fun viewBusinessIsClose() {
        repository = FakeAndroidTestRepositoryBusinessClose()
        ServiceLocator.dbusinessDetailsRepository = repository
        val fragmentArgs = Bundle().apply {
            putString("businessId", "1")
        }

        launchFragmentInContainer<DetailsBusinessFragment>(fragmentArgs, R.style.AppTheme)

        onView(ViewMatchers.withId(R.id.tv_business_status_open)).check(
            matches(
                withEffectiveVisibility(ViewMatchers.Visibility.GONE)
            )
        )

        onView(ViewMatchers.withId(R.id.tv_business_status_close)).check(
            matches(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
            )
        )
    }

    @Test
    fun viewBusinessIsOpen() {
        repository = FakeAndroidTestRepositoryBusinessOpen()
        ServiceLocator.dbusinessDetailsRepository = repository
        val fragmentArgs = Bundle().apply {
            putString("businessId", "1")
        }

        launchFragmentInContainer<DetailsBusinessFragment>(fragmentArgs, R.style.AppTheme)

        onView(ViewMatchers.withId(R.id.tv_business_status_open)).check(
            matches(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
            )
        )
        onView(ViewMatchers.withId(R.id.tv_business_status_close)).check(
            matches(
                withEffectiveVisibility(ViewMatchers.Visibility.GONE)
            )
        )
    }
}