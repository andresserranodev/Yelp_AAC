package com.puzzlebench.yelp_aac.repository.model

import com.puzzlebench.yelp_aac.presentation.details.BusinessDetails

data class BusinessDetailsState(
    val businessDetails: BusinessDetails?,
    val error: String = NO_ERROR
)
