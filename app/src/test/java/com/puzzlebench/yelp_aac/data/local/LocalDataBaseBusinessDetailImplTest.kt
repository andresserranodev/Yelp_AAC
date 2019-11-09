package com.puzzlebench.yelp_aac.data.local

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.yelp_aac.DummyBusinessDetailsFactory.getDummyBusinessDetails
import com.puzzlebench.yelp_aac.DummyBusinessDetailsFactory.getDummyListCategoriesEntity
import com.puzzlebench.yelp_aac.data.local.room.dao.CategoriesDao
import com.puzzlebench.yelp_aac.data.mapper.BusinessDetailMapper
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LocalDataBaseBusinessDetailImplTest {

    private val businessId = "BUSINESS_ID1"
    private lateinit var localDataBaseBusinessDetail: LocalDataBaseBusinessDetail
    private val localDataCategoriesEntity = getDummyListCategoriesEntity()
    private val businesDetails = getDummyBusinessDetails("1")

    private val categoriesDao = mock<CategoriesDao> {
        onBlocking { getCategoriesByBusinessId(businessId) } doReturn localDataCategoriesEntity
    }
    private val mapper = mock<BusinessDetailMapper>()

    @Before
    fun setUp() {
        localDataBaseBusinessDetail = LocalDataBaseBusinessDetailImpl(categoriesDao, mapper)
    }

    @Test
    fun getBusinessDetailsByBusinessId() {
        runBlocking {
            localDataBaseBusinessDetail.getBusinessDetailsByBusinessId(businessId)
            verify(categoriesDao).getCategoriesByBusinessId(businessId)
            verify(mapper).transformEntityToRepository(localDataCategoriesEntity)
        }
    }

    @Test
    fun deleteAllBusinessDetails() {
        runBlocking {
            localDataBaseBusinessDetail.deleteAllBusinessDetails()
            verify(categoriesDao).deleteAllCategories()
        }
    }
}