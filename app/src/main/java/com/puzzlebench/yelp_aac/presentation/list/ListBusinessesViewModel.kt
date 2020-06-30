package com.puzzlebench.yelp_aac.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzlebench.yelp_aac.presentation.model.Business
import com.puzzlebench.yelp_aac.repository.BusinessRepository
import kotlinx.coroutines.launch

class ListBusinessesViewModel internal constructor(private val businessRepository: BusinessRepository) :
    ViewModel() {

    val business: MutableLiveData<List<Business>> = MutableLiveData()
    val businessError: MutableLiveData<String> = MutableLiveData()

    fun getBusiness() {
        viewModelScope.launch {
            val businessData = businessRepository.getBusiness()
            if (businessData.error.isEmpty()) {
                business.postValue(businessData.businesses)
            } else {
                businessError.postValue(businessData.error)
            }
        }
    }
}
