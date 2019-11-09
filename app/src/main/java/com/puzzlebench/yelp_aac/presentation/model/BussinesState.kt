package com.puzzlebench.yelp_aac.presentation.model

const val NO_ERROR = ""

class BussinesState(
    val businesses: List<Business>,
    val error: String = NO_ERROR
)
