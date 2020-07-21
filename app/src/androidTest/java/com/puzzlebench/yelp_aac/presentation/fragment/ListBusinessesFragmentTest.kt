package com.puzzlebench.yelp_aac.presentation.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.puzzlebench.yelp_aac.FakeAndroidTestRepositoryBusiness
import com.puzzlebench.yelp_aac.FakeAndroidTestRepositoryBusinessOpen
import com.puzzlebench.yelp_aac.R
import com.puzzlebench.yelp_aac.presentation.di.ServiceLocator
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository
import com.puzzlebench.yelp_aac.repository.BusinessRepository
import com.puzzlebench.yelp_aac.utils.UtilsAction
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ListBusinessesFragmentTest {
    private lateinit var businessRepository: BusinessRepository
    private lateinit var businessDetailsRepository: BusinessDetailsRepository

    @Test
    fun whenItemClickedThemNavigateToDetailsFragment() {
        businessRepository = FakeAndroidTestRepositoryBusiness()
        ServiceLocator.businessRepository = businessRepository
        businessDetailsRepository = FakeAndroidTestRepositoryBusinessOpen()
        ServiceLocator.businessDetailsRepository = businessDetailsRepository
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.nav_graph)
        // Create a graphical Fragment
        val listBusinessesFragment = launchFragmentInContainer<ListBusinessesFragment>(null, R.style.AppTheme)
        // Set the NavController property on the fragment
        listBusinessesFragment.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.business_list_rv))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>
                (0, UtilsAction.performClickById(R.id.business_cv)))

        assertEquals(navController.currentDestination?.label, "details_business_fragment")
    }
}
