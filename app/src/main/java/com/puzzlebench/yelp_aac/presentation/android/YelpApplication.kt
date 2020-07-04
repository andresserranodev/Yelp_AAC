package com.puzzlebench.yelp_aac.presentation.android

import android.app.Application
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusinessImpl
import com.puzzlebench.yelp_aac.data.local.room.dao.BusinessDao
import com.puzzlebench.yelp_aac.presentation.di.ServiceLocator
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository
import com.puzzlebench.yelp_aac.repository.BusinessRepository

class YelpApplication : Application() {
    // TODO this can be improvement this using Dagger2 whit the Scopes
    val businessRepository: BusinessRepository
        get() = ServiceLocator.provideBusinessRepository(this)
    val businessDetailsRepository: BusinessDetailsRepository
        get() = ServiceLocator.provideBusinessDetailsRepository(this)

    val provideBusinessDao: BusinessDao
        get() = ServiceLocator.provideBusinessDao(this)
}
