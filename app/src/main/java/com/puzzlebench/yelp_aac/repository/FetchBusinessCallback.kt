package com.puzzlebench.yelp_aac.repository

import android.util.Log
import androidx.paging.PagedList
import com.puzzlebench.yelp_aac.data.local.room.dao.BusinessDao
import com.puzzlebench.yelp_aac.data.local.room.entity.BusinessEntity
import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import com.puzzlebench.yelp_aac.data.remote.retrofit.BusinessResponse
import com.puzzlebench.yelp_aac.data.remote.retrofit.YelpApiV3
import com.puzzlebench.yelp_aac.data.remote.retrofit.YelpResponse
import com.puzzlebench.yelp_aac.presentation.utils.PagingRequestHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

const val GET_BUSINESS_PAGED_LIMIT = 6

class FetchBusinessCallback constructor(
    private val businessDao: BusinessDao,
    private val api: YelpApiV3,
    private val mapper: BusinessMapper
) : PagedList.BoundaryCallback<BusinessEntity>() {
    private val executor = Executors.newSingleThreadExecutor()
    private val helper = PagingRequestHelper(executor)

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) { helperCallback ->
            api.getBusiness(limit = GET_BUSINESS_PAGED_LIMIT)
                .enqueue(object : Callback<YelpResponse> {

                    override fun onFailure(call: Call<YelpResponse>?, t: Throwable) {
                        // 3
                        Log.e("RedditBoundaryCallback", "Failed to load data!")
                        helperCallback.recordFailure(t)
                    }

                    override fun onResponse(
                        call: Call<YelpResponse>?,
                        response: Response<YelpResponse>
                    ) {
                        // 4
                        response.body()?.businesses?.let { businessResponse ->
                            executor.execute {
                                updateLocal(businessResponse)
                                helperCallback.recordSuccess()
                            }
                        }
                    }
                })
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: BusinessEntity) {
        super.onItemAtEndLoaded(itemAtEnd)
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) { helperCallback ->
            executor.execute {
                api.getBusiness(
                    limit = GET_BUSINESS_PAGED_LIMIT,
                    offset = businessDao.getAllBusiness().size
                )
                    .enqueue(object : Callback<YelpResponse> {

                        override fun onFailure(call: Call<YelpResponse>?, t: Throwable) {
                            Log.e("RedditBoundaryCallback", "Failed to load data!")
                            helperCallback.recordFailure(t)
                        }

                        override fun onResponse(
                            call: Call<YelpResponse>?,
                            response: Response<YelpResponse>
                        ) {
                            // 4
                            response.body()?.businesses?.let { businessResponse ->
                                executor.execute {
                                    updateLocal(businessResponse)
                                    helperCallback.recordSuccess()
                                }
                            }
                        }
                    })
            }
        }
    }

    private fun updateLocal(businessResponse: List<BusinessResponse>) {
        val response = businessResponse.map {
            mapper.transformServiceToRepository(it)
        }
        businessDao.insert(mapper.transformRepositoryToEntity(response))
    }
}