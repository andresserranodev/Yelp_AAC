package com.puzzlebench.yelp_aac.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_business_table")
data class PhotoEntity constructor(
    var businessId: String,
    var photoUrl: String
) {
    @PrimaryKey(autoGenerate = true)
    var photoId: Long? = 0
}
