package com.puzzlebench.yelp_aac.data.remote.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface YelpApiV3 {

    @GET("v3/businesses/{id}")
    suspend fun getBusinessDetailsById(@Path("id") businessId: String): BusinessDetailResponse

    @GET("v3/businesses/search")
    suspend fun getBusinessByLocation(@Query("location") location: String): YelpResponse
}
