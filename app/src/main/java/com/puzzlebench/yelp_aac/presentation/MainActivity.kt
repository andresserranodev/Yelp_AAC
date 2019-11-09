package com.puzzlebench.yelp_aac.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puzzlebench.yelp_aac.R
import com.puzzlebench.yelp_aac.ServiceLocator
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusiness
import com.puzzlebench.yelp_aac.data.local.LocalDataBaseBusinessDetail
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchBusinessDetailsById
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchSwitzerlandBusinesses
import com.puzzlebench.yelp_aac.presentation.model.BusinessDetailsState
import com.puzzlebench.yelp_aac.presentation.model.BussinesState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var mJob: Job
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    private lateinit var remoteFetchSwitzerlandBusinesses: RemoteFetchSwitzerlandBusinesses
    private lateinit var remoteFetchBusinessDetailsById: RemoteFetchBusinessDetailsById
    private lateinit var localDataBaseBusiness: LocalDataBaseBusiness
    private lateinit var localDataBaseBusinessDetail: LocalDataBaseBusinessDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mJob = Job()
        remoteFetchSwitzerlandBusinesses = ServiceLocator.provideRemoteFetchSwitzerlandBusinesses()
        remoteFetchBusinessDetailsById = ServiceLocator.provideRemoteFetchBusinessDetailsByIdImpl()
        localDataBaseBusiness = ServiceLocator.provideBusinessLocalDataSource(this.baseContext)
        localDataBaseBusinessDetail =
            ServiceLocator.provideLocalDataBaseBusinessDetail(this.baseContext)
        launch {
            val businessState: BussinesState
            val localData = localDataBaseBusiness.getBusiness()
            businessState = if (localData.businesses.isEmpty()) {
                val response = remoteFetchSwitzerlandBusinesses.fetchSwitzerlandBusiness()
                response.businesses.map {
                    localDataBaseBusiness.saveBusiness(it)
                }
                response
            } else {
                localData
            }
            if (businessState.error.isEmpty()) {
                textView.text = businessState.businesses[0].businessId
            } else {
                textView.text = businessState.error
            }
        }
        launch {

            val businessId = "KeNGoOn5jsAtsq-AXyDWzQ"
            val businessDetailsState: BusinessDetailsState
            val localData =
                localDataBaseBusinessDetail.getBusinessDetailsByBusinessId(businessId)
            businessDetailsState = if (localData.businessDetails == null) {
                val remoteData =
                    remoteFetchBusinessDetailsById.fetchBusinessDetailsById(businessId)
                remoteData.businessDetails?.let {
                    localDataBaseBusinessDetail.insertBusinessDetails(it)
                }
                remoteData
            } else {
                localData
            }
            if (businessDetailsState.error.isEmpty()) {
                textView_details_category.text =
                    businessDetailsState.businessDetails?.categories.toString()
                textView_details_photo.text =
                    businessDetailsState.businessDetails?.photos.toString()
            } else {
                textView_details_category.text = businessDetailsState.error
            }

        }


    }
}
