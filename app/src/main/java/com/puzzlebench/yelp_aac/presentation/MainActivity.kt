package com.puzzlebench.yelp_aac.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puzzlebench.yelp_aac.R
import com.puzzlebench.yelp_aac.ServiceLocator
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchSwitzerlandBusinesses
import com.puzzlebench.yelp_aac.data.remote.RemoteFetchSwitzerlandBusinessesIml
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mJob = Job()
        remoteFetchSwitzerlandBusinesses = ServiceLocator.provideFetchSwitzerlandBusiness()
        launch {
            val response = remoteFetchSwitzerlandBusinesses.fetchSwitzerlandBusiness()
            if (response.error.isEmpty()) {
                textView.text = response.businesses[0].businessId
            } else {
                textView.text = response.error
            }
        }


    }
}
