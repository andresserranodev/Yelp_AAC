package com.puzzlebench.yelp_aac.data.remote.retrofit

import com.google.gson.annotations.SerializedName

class BusinessResponse(
    @SerializedName("id") val businessId: String,
    val name: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("display_phone") val displayPhone: String,
    val price: String?
)

class YelpResponse(val businesses: List<BusinessResponse>)

class BusinessDetailResponse(
    @SerializedName("id") val businessId: String,
    val categories: List<CategoriesResponse>,
    val photos: List<String>
)

class CategoriesResponse(
    val title: String
)