package com.puzzlebench.yelp_aac.presentation

import android.app.Application
import com.puzzlebench.yelp_aac.presentation.di.ServiceLocator
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository
import com.puzzlebench.yelp_aac.repository.BusinessRepository

class YelpApplication : Application() {
    // TODO this can be improvement this using Dagger2 whit the Scopes
    val businessRepository: BusinessRepository
        get() = ServiceLocator.provideBusinessRepository(this)
    val businessDetailsRepository: BusinessDetailsRepository
        get() = ServiceLocator.provideBusinessDetailsRepository(this)
}
