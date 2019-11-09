package com.puzzlebench.yelp_aac.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "business_categories_table")
data class CategoriesEntity constructor(
    @PrimaryKey(autoGenerate = true) var categoriesId: Long,
    var businessId: String,
    val title: String
)
