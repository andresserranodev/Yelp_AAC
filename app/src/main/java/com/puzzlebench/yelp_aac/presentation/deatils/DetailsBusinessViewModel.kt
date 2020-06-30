package com.puzzlebench.yelp_aac.presentation.deatils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository
import kotlinx.coroutines.launch

class DetailsBusinessViewModel internal constructor(
    private val businessDetailsRepository: BusinessDetailsRepository,
    private val businessId: String
) :
    ViewModel() {

    val business = businessDetailsRepository.getBusinessById(businessId)
    val businessCategories = MutableLiveData<List<String>>()
    val businessPhotos = MutableLiveData<List<String>>()
    val errorFetchingData = MutableLiveData<String>()

    fun getBusinessDetails() {
        viewModelScope.launch {
            val result = businessDetailsRepository.getBusinessDetailsById(businessId)
            if (result.error.isEmpty()) {
                businessCategories.postValue(result.businessDetails?.categories)
                businessPhotos.postValue(result.businessDetails?.photos)
            } else {
                errorFetchingData.postValue(result.error)
            }
        }
    }
}
