package com.puzzlebench.yelp_aac

import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchSwitzerlandBusinesses
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchSwitzerlandBusinessesIml
import com.puzzlebench.yelp_aac.data.remote.retofit.RetrofitClient

object ServiceLocator {

    private fun provideYelpApiV3() = RetrofitClient.makeServiceYelpApiV3()
    private fun provideBusinessMapper() = BusinessMapper()

    fun provideFetchSwitzerlandBusiness(): RemoteFetchSwitzerlandBusinesses {
        return RemoteFetchSwitzerlandBusinessesIml(
            provideYelpApiV3(),
            provideBusinessMapper()
        )
    }
}