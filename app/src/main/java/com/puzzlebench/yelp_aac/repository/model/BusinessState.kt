package com.puzzlebench.yelp_aac.repository.model

import com.puzzlebench.yelp_aac.presentation.model.Business

const val NO_ERROR = ""

class BusinessState(
    val businesses: List<Business>,
    val error: String = NO_ERROR
)
