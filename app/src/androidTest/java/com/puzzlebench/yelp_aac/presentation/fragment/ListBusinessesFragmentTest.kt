package com.puzzlebench.yelp_aac.presentation.fragment

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.puzzlebench.yelp_aac.FakeAndroidTestRepositoryBusiness
import com.puzzlebench.yelp_aac.R
import com.puzzlebench.yelp_aac.presentation.di.ServiceLocator
import com.puzzlebench.yelp_aac.repository.BusinessRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
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
        onView(withId(R.id.business_list_rv))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>
                (0,TypeChileAction.typeText(R.id.business_cv)))
        Thread.sleep(500.toLong())
    }
}

class TypeChileAction {
    companion object{
        fun typeText(chileId:Int): ViewAction{
            return object :ViewAction{
                override fun getDescription(): String=""

                override fun getConstraints(): Matcher<View> {
                    return  allOf(isAssignableFrom(ViewGroup::class.java))
                }

                override fun perform(uiController: UiController?, view: View?) {
                    val v = view?.findViewById<ViewGroup>(chileId)
                    v?.performClick()
                }
            }
        }
    }
}