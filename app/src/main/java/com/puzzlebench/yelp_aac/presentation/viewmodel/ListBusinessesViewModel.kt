package com.puzzlebench.yelp_aac.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.puzzlebench.yelp_aac.data.local.room.dao.BusinessDao
import com.puzzlebench.yelp_aac.data.local.room.entity.BusinessEntity
import com.puzzlebench.yelp_aac.repository.FetchBusinessCallback
import com.puzzlebench.yelp_aac.repository.GET_BUSINESS_PAGED_LIMIT

class ListBusinessesViewModel internal constructor(
    private val fetchBusinessCallback: FetchBusinessCallback,
    private val businessDao: BusinessDao
) :
    ViewModel() {

    private val config = PagedList.Config.Builder()
        .setPageSize(GET_BUSINESS_PAGED_LIMIT)
        .setEnablePlaceholders(false)
        .build()

    val liveData = initializedPagedListBuilder(config).build()

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, BusinessEntity> {

        return LivePagedListBuilder(
            businessDao.getBusinessPager(),
            config
        ).setBoundaryCallback(fetchBusinessCallback)
    }
}
