package com.puzzlebench.yelp_aac.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzlebench.yelp_aac.presentation.viewmodel.state.DetailsBusinessViewState
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository
import kotlinx.coroutines.launch

class DetailsBusinessViewModel internal constructor(
    private val businessDetailsRepository: BusinessDetailsRepository,
    private val businessId: String
) :
    ViewModel() {

    val business = businessDetailsRepository.getBusinessById(businessId)
    private val detailsBusinessViewStateMutable = MutableLiveData<DetailsBusinessViewState>()
    val detailsBusinessViewState: LiveData<DetailsBusinessViewState>
        get() = detailsBusinessViewStateMutable


    fun getBusinessDetails() {
        viewModelScope.launch {
            val result = businessDetailsRepository.getBusinessDetailsById(businessId)
            if (result.error.isEmpty()) {
                detailsBusinessViewStateMutable.value = result.businessDetails?.categories?.let {
                    DetailsBusinessViewState.ShowCategories(
                        it
                    )
                }
                detailsBusinessViewStateMutable.value = result.businessDetails?.photos?.let {
                    DetailsBusinessViewState.ShowPhotos(
                        it
                    )
                }
            } else {
                detailsBusinessViewStateMutable.value =
                    DetailsBusinessViewState.ShowErrorMessage(result.error)
            }
        }
    }
}
