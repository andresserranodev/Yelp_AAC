package com.puzzlebench.yelp_aac

import android.content.Context
import androidx.room.Room
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusinessDetail
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusinessDetailImpl
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusinessImpl
import com.puzzlebench.yelp_aac.data.local.room.YelpDataBase
import com.puzzlebench.yelp_aac.data.mapper.BusinessDetailMapper
import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessDetailsByIdImpl
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchSwitzerlandBusinesses
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchSwitzerlandBusinessesImpl
import com.puzzlebench.yelp_aac.data.remote.retrofit.RetrofitClient

object ServiceLocator {

    private var database: YelpDataBase? = null


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

    fun provideBusinessLocalDataSource(context: Context): LocalDataBaseBusiness {
        val database = database
            ?: createDataBase(context)
        return LocalDataBaseBusinessImpl(
            database.businessDao(),
            BusinessMapper()
        )
    }

    fun provideLocalDataBaseBusinessDetail(context: Context): LocalDataBaseBusinessDetail {
        val database = database
            ?: createDataBase(context)
        return LocalDataBaseBusinessDetailImpl(
            database.categoriesDao(),
            provideBusinessDetailMapper()
        )
    }

    private fun provideYelpApiV3() = RetrofitClient.makeServiceYelpApiV3()
    private fun provideBusinessMapper() = BusinessMapper()
    private fun provideBusinessDetailMapper() = BusinessDetailMapper()


    private fun createDataBase(context: Context): YelpDataBase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            YelpDataBase::class.java, "Yep.db"
        ).build()
        database = result
        return result
    }
}