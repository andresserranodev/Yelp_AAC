package com.puzzlebench.yelp_aac.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.puzzlebench.yelp_aac.R
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository
import com.puzzlebench.yelp_aac.repository.BusinessRepository
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

    private lateinit var businessRepository: BusinessRepository
    private lateinit var businessDetailsRepository: BusinessDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mJob = Job()
        businessRepository = (this.applicationContext as YelpApplication).businessRepository
        businessDetailsRepository =
            (this.applicationContext as YelpApplication).businessDetailsRepository
        launch {
            val businessState = businessRepository.getBusiness()
            if (businessState.error.isEmpty()) {
                textView.text = businessState.businesses[0].businessId
            } else {
                textView.text = businessState.error
            }
        }
        launch {
            val businessId = "KeNGoOn5jsAtsq-AXyDWzQ"
            val businessDetailsState = businessDetailsRepository.getBusinessDetailsById(businessId)
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
