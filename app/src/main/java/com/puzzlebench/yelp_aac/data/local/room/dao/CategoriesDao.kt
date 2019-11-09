package com.puzzlebench.yelp_aac.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.puzzlebench.yelp_aac.data.local.room.entity.CategoriesEntity

@Dao
interface CategoriesDao {
    @Query("SELECT * FROM business_categories_table WHERE businessId = :businessId")
    suspend fun getCategoriesByBusinessId(businessId: String): List<CategoriesEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategories(categoriesEntity: CategoriesEntity)

    @Query("DELETE FROM business_categories_table")
    suspend fun deleteAllCategories()
}