package com.puzzlebench.yelp_aac

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.puzzlebench.yelp_aac.DummyBusinessFactoryAndroid.getDummyBusinessResponseIsClose
import com.puzzlebench.yelp_aac.DummyBusinessFactoryAndroid.getDyummyBusinessDetailsState
import com.puzzlebench.yelp_aac.presentation.model.Business
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository
import com.puzzlebench.yelp_aac.repository.model.BusinessDetailsState
import kotlinx.coroutines.runBlocking

class FakeAndroidTestRepositoryBusinessClose : BusinessDetailsRepository {

    private val observableTasks = MutableLiveData<Business>()

    override suspend fun getBusinessDetailsById(businessId: String): BusinessDetailsState {
        return getDyummyBusinessDetailsState()
    }

    override fun getBusinessById(businessId: String): LiveData<Business> {
        runBlocking {
            observableTasks.postValue(getDummyBusinessResponseIsClose("1"))
        }
        return observableTasks
    }
}
