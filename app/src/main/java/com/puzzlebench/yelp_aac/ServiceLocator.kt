package com.puzzlebench.yelp_aac

import com.puzzlebench.yelp_aac.data.mapper.BusinessDetailMapper
import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessDetailsByIdImpl
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchSwitzerlandBusinesses
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchSwitzerlandBusinessesImpl
import com.puzzlebench.yelp_aac.data.remote.retrofit.RetrofitClient

object ServiceLocator {

    private fun provideYelpApiV3() = RetrofitClient.makeServiceYelpApiV3()
    private fun provideBusinessMapper() = BusinessMapper()
    private fun provideBusinessDetailMapper() = BusinessDetailMapper()


    fun provideRemoteFetchSwitzerlandBusinesses(): RemoteFetchSwitzerlandBusinesses {
        return RemoteFetchSwitzerlandBusinessesImpl(
            provideYelpApiV3(),
            provideBusinessMapper()
        )
    }

    fun provideRemoteFetchBusinessDetailsByIdImpl() =
        RemoteFetchBusinessDetailsByIdImpl(
            provideYelpApiV3(),
            provideBusinessDetailMapper()
        )
}