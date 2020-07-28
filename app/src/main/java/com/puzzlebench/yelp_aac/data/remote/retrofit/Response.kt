package com.puzzlebench.yelp_aac.data.remote.retrofit

import com.google.gson.annotations.SerializedName
import com.puzzlebench.yelp_aac.data.mapper.PRICE_EMPTY_VALUE
import com.puzzlebench.yelp_aac.presentation.model.Business

class BusinessResponse(
    @SerializedName("id") val businessId: String,
    val name: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("display_phone") val displayPhone: String,
    @SerializedName("is_closed") val isClosed: Boolean,
    val location: Location,
    val rating: Float,
    val price: String?
)

fun BusinessResponse.transformServiceToRepository(locale: String): Business = Business(
    this.businessId,
    this.name,
    this.imageUrl,
    this.displayPhone,
    this.location.address1,
    this.isClosed,
    this.rating,
    this.price ?: PRICE_EMPTY_VALUE,
    locale
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

class Location(
    val address1: String
)
