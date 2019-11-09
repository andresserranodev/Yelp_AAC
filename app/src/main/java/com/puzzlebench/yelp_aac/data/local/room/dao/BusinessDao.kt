package com.puzzlebench.yelp_aac.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.puzzlebench.yelp_aac.data.local.room.entity.BusinessEntity

@Dao
interface BusinessDao {

    @Query("SELECT * FROM BUSINESS_TABLE")
    suspend fun getBusiness(): List<BusinessEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBusiness(business: BusinessEntity)


    @Query("DELETE FROM BUSINESS_TABLE")
    suspend fun deleteAllBusiness()
}