package com.puzzlebench.yelp_aac.presentation

data class BusinessDetailsState(
    val businessDetails: BusinessDetails?,
    val error: String = NO_ERROR
)