package com.puzzlebench.yelp_aac.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzlebench.yelp_aac.repository.BusinessRepository
import kotlinx.coroutines.launch

class ListBusinessViewModel internal constructor(private val businessRepository: BusinessRepository) :
    ViewModel() {

    val business: MutableLiveData<String> = MutableLiveData()

    fun getBussines() {
        viewModelScope.launch {
            val businessData = businessRepository.getBusiness()
            if (businessData.error.isEmpty()) {
                business.postValue(businessData.businesses.toString())
            } else {
                business.postValue(businessData.error)

            }
        }
    }
}