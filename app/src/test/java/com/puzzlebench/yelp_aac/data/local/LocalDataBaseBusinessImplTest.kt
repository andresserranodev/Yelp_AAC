package com.puzzlebench.yelp_aac.data.local

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getDummyBusiness
import com.puzzlebench.yelp_aac.DummyBusinessFactory.getDummyListBusinessEntity
import com.puzzlebench.yelp_aac.data.local.room.dao.BusinessDao
import com.puzzlebench.yelp_aac.data.mapper.BusinessMapper
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LocalDataBaseBusinessImplTest {

    private lateinit var businessLocalDataBaseDataImpl: LocalDataBaseBusiness
    private val businessEntity = getDummyListBusinessEntity()

    private val businessDao = mock<BusinessDao> {
        onBlocking { getBusiness() } doReturn businessEntity
    }
    private var businessMapper = mock<BusinessMapper>()

    @Before
    fun setUp() {
        businessLocalDataBaseDataImpl = LocalDataBaseBusinessImpl(businessDao, businessMapper)
    }

    @Test
    fun getBusiness() {
        runBlocking {
            businessLocalDataBaseDataImpl.getBusiness()
            verify(businessDao).getBusiness()
            businessEntity.forEach {
                verify(businessMapper).transformEntityToRepository(it)
            }
        }
    }

    @Test
    fun saveBusiness() {
        runBlocking {
            val business = getDummyBusiness("1")
            businessLocalDataBaseDataImpl.saveBusiness(business)
            verify(businessMapper).transformRepositoryToEntity(business)
            verify(businessDao).insertBusiness(businessMapper.transformRepositoryToEntity(business))
        }
    }

    @Test
    fun deleteAll() {
        runBlocking {
            businessLocalDataBaseDataImpl.deleteAll()
            verify(businessDao).deleteAllBusiness()
        }
    }
}
