package com.puzzlebench.yelp_aac.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.puzzlebench.yelp_aac.data.local.room.entity.PhotoEntity

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo_business_table WHERE businessId = :businessId")
    suspend fun getPhotosByBusinessId(businessId: String): List<PhotoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPhoto(photoEntity: PhotoEntity)

    @Query("DELETE FROM photo_business_table")
    suspend fun deleteAllPhotos()
}
