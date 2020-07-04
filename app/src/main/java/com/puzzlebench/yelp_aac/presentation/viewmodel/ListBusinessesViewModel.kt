package com.puzzlebench.yelp_aac.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzlebench.yelp_aac.presentation.viewmodel.state.BusinessViewState
import com.puzzlebench.yelp_aac.repository.BusinessRepository
import kotlinx.coroutines.launch

class ListBusinessesViewModel internal constructor(private val businessRepository: BusinessRepository) :
    ViewModel() {

    private val businessMutable: MutableLiveData<BusinessViewState> = MutableLiveData()
    val businessState: LiveData<BusinessViewState>
        get() = businessMutable

    fun getBusiness() {
        /*
        viewModelScope.launch {
            val businessData = businessRepository.getBusiness()
            if (businessData.error.isEmpty()) {
                businessMutable.value = BusinessViewState.ShowBusiness(businessData.businesses)
            } else {
                businessMutable.value = BusinessViewState.ShowErrorMessage(businessData.error)
            }
        }*/
    }
}
