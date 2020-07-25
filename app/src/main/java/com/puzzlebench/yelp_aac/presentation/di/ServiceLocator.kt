package com.puzzlebench.yelp_aac.presentation.di

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusinessDetail
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusinessDetailImpl
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusinessImpl
import com.puzzlebench.yelp_aac.data.local.room.YelpDataBase
import com.puzzlebench.yelp_aac.data.mapper.BusinessDetailMapper
import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessDetailsByIdImpl
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessesByLocale
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessesByLocaleImpl
import com.puzzlebench.yelp_aac.data.remote.retrofit.RetrofitClient
import com.puzzlebench.yelp_aac.repository.*

object ServiceLocator {

    private var database: YelpDataBase? = null

    @Volatile
    var businessRepository: BusinessRepository? = null
        @VisibleForTesting set

    @Volatile
    var businessDetailsRepository: BusinessDetailsRepository? = null
        @VisibleForTesting set

    fun provideBusinessRepository(context: Context): BusinessRepository {
        synchronized(this) {
            return businessRepository
                ?: createBusinessRepository(
                    context
                )
        }
    }

    fun provideBusinessDetailsRepository(context: Context): BusinessDetailsRepository {
        synchronized(this) {
            return businessDetailsRepository
                ?: createBusinessDetailsRepository(
                    context
                )
        }
    }

    private fun provideRemoteFetchSwitzerlandBusinesses(): RemoteFetchBusinessesByLocale {
        return RemoteFetchBusinessesByLocaleImpl(
            provideYelpApiV3(),
            provideBusinessMapper()
        )
    }

    private fun provideRemoteFetchBusinessDetailsByIdImpl() =
        RemoteFetchBusinessDetailsByIdImpl(
            provideYelpApiV3(),
            provideBusinessDetailMapper()
        )

    private fun provideBusinessLocalDataSource(context: Context): LocalDataBaseBusiness {
        val database = database
            ?: createDataBase(context)
        return LocalDataBaseBusinessImpl(
            database.businessDao(),
            BusinessMapper()
        )
    }

    private fun provideLocalDataBaseBusinessDetail(context: Context): LocalDataBaseBusinessDetail {
        val database = database
            ?: createDataBase(context)
        return LocalDataBaseBusinessDetailImpl(
            database.businessDao(),
            database.categoriesDao(),
            database.photoDao(),
            provideBusinessDetailMapper(),
            provideBusinessMapper()
        )
    }

    private fun createBusinessRepository(context: Context): BusinessRepository {
        val businessRepositoryImpl =
            BusinessRepositoryImpl(
                provideRemoteFetchSwitzerlandBusinesses(),
                provideBusinessLocalDataSource(
                    context
                )
            )
        businessRepository = businessRepositoryImpl
        return businessRepositoryImpl
    }

    private fun createBusinessDetailsRepository(context: Context): BusinessDetailsRepository {
        val businessDetailsRepositoryImpl =
            BusinessDetailsRepositoryImpl(
                provideRemoteFetchBusinessDetailsByIdImpl(),
                provideLocalDataBaseBusinessDetail(context)
            )
        businessDetailsRepository = businessDetailsRepositoryImpl
        return businessDetailsRepositoryImpl
    }

    private fun createUpdateRepository(context: Context): UpdateRepository =
        UpdateRepositoryImpl(
            provideRemoteFetchSwitzerlandBusinesses(),
            provideBusinessLocalDataSource(context),
            provideLocalDataBaseBusinessDetail(context)
        )

    private fun createDataBase(context: Context): YelpDataBase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            YelpDataBase::class.java, "Yep.db"
        ).build()
        database = result
        return result
    }

    private fun provideYelpApiV3() = RetrofitClient.makeServiceYelpApiV3()
    private fun provideBusinessMapper() = BusinessMapper()
    private fun provideBusinessDetailMapper() = BusinessDetailMapper()

    fun provideUpdateRepository(context: Context): UpdateRepository {
        return createUpdateRepository(context)
    }
}
