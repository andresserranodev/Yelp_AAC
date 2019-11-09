package com.puzzlebench.yelp_aac.data.remote.retofit

import retrofit2.http.GET

interface YelpApiV3 {
    @GET("v3/businesses/search?location=Switzerland")
    suspend fun getSwitzerlandBusiness(): YelpResponse
}