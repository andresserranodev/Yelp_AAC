package com.puzzlebench.yelp_aac.repository.model

data class BusinessDetailsState(
    val businessDetails: BusinessDetails?,
    val error: String = NO_ERROR
)
