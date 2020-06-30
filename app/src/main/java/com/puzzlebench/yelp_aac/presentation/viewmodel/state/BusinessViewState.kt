package com.puzzlebench.yelp_aac.presentation.viewmodel.state

import com.puzzlebench.yelp_aac.presentation.model.Business

sealed class BusinessViewState {
    class ShowErrorMessage(val message: String) : BusinessViewState()
    class ShowBusiness(val business: List<Business>) : BusinessViewState()
}