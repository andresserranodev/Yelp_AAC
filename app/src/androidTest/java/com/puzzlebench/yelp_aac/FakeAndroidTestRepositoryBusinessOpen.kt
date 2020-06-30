package com.puzzlebench.yelp_aac

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.puzzlebench.yelp_aac.presentation.list.Business
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository
import com.puzzlebench.yelp_aac.repository.model.BusinessDetailsState
import kotlinx.coroutines.runBlocking

class FakeAndroidTestRepositoryBusinessOpen : BusinessDetailsRepository {

    private val observableTasks = MutableLiveData<Business>()


    override suspend fun getBusinessDetailsById(businessId: String): BusinessDetailsState {
        return DummyBusinessFactoryAndroid.getDyummyBusinessDetailsState()
    }

    override fun getBusinessById(businessId: String): LiveData<Business> {
        runBlocking {
            observableTasks.postValue(DummyBusinessFactoryAndroid.getDummyBusinessResponseIsOpen("1"))
        }
        return observableTasks
    }
}
