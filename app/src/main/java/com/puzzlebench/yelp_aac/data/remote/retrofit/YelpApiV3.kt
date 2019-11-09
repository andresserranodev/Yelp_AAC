package com.puzzlebench.yelp_aac.data.remote.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface YelpApiV3 {
    @GET("v3/businesses/search?location=Switzerland")
    suspend fun getSwitzerlandBusiness(): YelpResponse
    @GET("v3/businesses/{id}")
    suspend fun getBusinessDetailsById(@Path("id") businessId: String): BusinessDetailResponse
}