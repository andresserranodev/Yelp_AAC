package com.puzzlebench.yelp_aac.data.remote.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface YelpApiV3 {
    @GET("v3/businesses/search?location=Switzerland")
    suspend fun getSwitzerlandBusiness(): YelpResponse

    @GET("v3/businesses/search?location=Switzerland")
    fun getBusiness(
        @Query("limit") loadSize: Int = 6,
        @Query("offset") offset: Int? = null
    ): Call<YelpResponse>

    @GET("v3/businesses/{id}")
    suspend fun getBusinessDetailsById(@Path("id") businessId: String): BusinessDetailResponse
}
