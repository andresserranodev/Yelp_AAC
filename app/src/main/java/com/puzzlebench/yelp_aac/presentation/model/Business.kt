package com.puzzlebench.yelp_aac.presentation.model

data class Business(
    val businessId: String,
    val name: String,
    val imageUrl: String,
    val displayPhone: String,
    val displayAddress: String,
    val isClosed: Boolean,
    val rating: Float,
    val price: String
)
