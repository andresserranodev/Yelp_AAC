package com.puzzlebench.yelp_aac.presentation.model

data class BusinessDetailsState(
    val businessDetails: BusinessDetails?,
    val error: String = NO_ERROR
)