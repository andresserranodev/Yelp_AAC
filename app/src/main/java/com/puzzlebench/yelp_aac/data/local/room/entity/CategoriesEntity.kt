package com.puzzlebench.yelp_aac.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "business_categories_table")
data class CategoriesEntity constructor(
    var businessId: String,
    val title: String
) {
    @PrimaryKey(autoGenerate = true)
    var categoriesId: Long = 0
}
