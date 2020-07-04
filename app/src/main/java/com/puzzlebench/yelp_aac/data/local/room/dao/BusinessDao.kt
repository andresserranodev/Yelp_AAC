package com.puzzlebench.yelp_aac.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.puzzlebench.yelp_aac.data.local.room.entity.BusinessEntity

@Dao
interface BusinessDao {

    @Query("SELECT * FROM BUSINESS_TABLE")
    suspend fun getBusiness(): List<BusinessEntity>

    @Query("SELECT * FROM BUSINESS_TABLE")
    fun getBusinessPager(): DataSource.Factory<Int, BusinessEntity>

    @Query("SELECT * from BUSINESS_TABLE WHERE businessId = :businessId")
    fun getBusinessById(businessId: String): LiveData<BusinessEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBusiness(business: BusinessEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(business: List<BusinessEntity>)

    @Query("SELECT * FROM BUSINESS_TABLE")
    fun getAllBusiness(): List<BusinessEntity>

    @Query("DELETE FROM BUSINESS_TABLE")
    suspend fun deleteAllBusiness()
}
